package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.youssef.kotlinflowts.editor.kotlinflowts.column.ColumnComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.BlockComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ChartComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DateFieldComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DropdownComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ImageComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.MultiSelectComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.NumberFieldComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.RichTextComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.SignatureComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TableComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextFieldAreaComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextFieldComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.row.RowComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.ComponentEvent
import com.youssef.kotlinflowts.manager.kotlinflowts.Mode
import com.youssef.kotlinflowts.models.kotlinflowts.Screen
import com.youssef.kotlinflowts.utils.hoverSelect

@Composable
internal fun RowScope.KfLayoutComposable(
    screen: Screen,
    selectedComponentId: String,
    componentEditors: List<ComponentEditor>,
    onBlur: ((event: ComponentEvent) -> Unit)? = null,
    onFocus: ((event: ComponentEvent) -> Unit)? = null,
    onComponentChange: ((event: ComponentEvent) -> Unit)? = null,
    showUnsupportedComponents: Boolean = false,
    separatorComposable: @Composable () -> Unit,
    select: (ComponentEditor) -> Unit,
) {
    fun <T> ComponentEditor.emit(signal: Signal<T>) = when (signal) {
        is Signal.Focus  -> onFocus?.invoke(ComponentEvent(this, screen))
        is Signal.Blur   -> onBlur?.invoke(ComponentEvent(this, screen))
        is Signal.Change -> onComponentChange?.invoke(ComponentEvent(this, screen))
    }

    componentEditors.forEach { componentEditor ->
        val isSelected by remember(selectedComponentId) { mutableStateOf(selectedComponentId == componentEditor.id) }

        key(componentEditor.id) {  // Use key for stable identity
            when (componentEditor) {
                is TextComponentEditor -> KfTextComponent(
                    editor = componentEditor,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )


                is TextFieldComponentEditor -> KfTextFieldComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is NumberFieldComponentEditor -> KfNumberFieldComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is DateFieldComponentEditor -> KfDateTimeFieldComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    format = componentEditor.comp.format,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is MultiSelectComponentEditor -> KfSelectComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is DropdownComponentEditor    -> KfDropComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is ImageComponentEditor       -> KfImageComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onUpload = null,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is SignatureComponentEditor   -> KfSignatureComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is TableComponentEditor       -> KfTableComponent(
                    editor = componentEditor,
                    screen = screen,
                    previewRows = 5,
                    mode = Mode.fill,
                    onUpload = null,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is TextFieldAreaComponentEditor -> KfTextFieldArea(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is ChartComponentEditor       -> KfChartComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is BlockComponentEditor       -> KfBlockComponent(
                    component = componentEditor.comp,
                    position = null,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is RichTextComponentEditor    -> KfRichTextComponent(
                    component = componentEditor.comp,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is ColumnComponentEditor      -> KfColumnComponent(
                    editor = componentEditor,
                    screen = screen,
                    selectedComponentId = selectedComponentId,
                    isSelected = selectedComponentId == componentEditor.id,
                    onBlur = onBlur,
                    onFocus = onFocus,
                    onComponentChange = onComponentChange,
                    select = select,
                )

                is RowComponentEditor         -> KfRowComponent(
                    editor = componentEditor,
                    screen = screen,
                    selectedComponentId = selectedComponentId,
                    isSelected = selectedComponentId == componentEditor.id,
                    onBlur = onBlur,
                    onFocus = onFocus,
                    onComponentChange = onComponentChange,
                    select = select,
                )

                else                          -> if (showUnsupportedComponents) {
                    Text("Unsupported Component of type = ${componentEditor.type}")
                }
            }
            separatorComposable()
        }
    }
}


@Composable
internal fun ColumnScope.KfLayoutComposable(
    screen: Screen,
    selectedComponentId: String,
    componentEditors: List<ComponentEditor>,
    onBlur: ((event: ComponentEvent) -> Unit)? = null,
    onFocus: ((event: ComponentEvent) -> Unit)? = null,
    onComponentChange: ((event: ComponentEvent) -> Unit)? = null,
    showUnsupportedComponents: Boolean = false,
    separatorComposable: @Composable () -> Unit,
    select: (ComponentEditor) -> Unit,
) {
    fun <T> ComponentEditor.emit(signal: Signal<T>) = when (signal) {
        is Signal.Focus  -> onFocus?.invoke(ComponentEvent(this, screen))
        is Signal.Blur   -> onBlur?.invoke(ComponentEvent(this, screen))
        is Signal.Change -> onComponentChange?.invoke(ComponentEvent(this, screen))
    }

    componentEditors.forEach { componentEditor ->
        val isSelected by remember(selectedComponentId) { mutableStateOf(selectedComponentId == componentEditor.id) }

        key(componentEditor.id) {  // Use key for stable identity
            when (componentEditor) {
                is TextComponentEditor -> KfTextComponent(
                    editor = componentEditor,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is TextFieldComponentEditor -> KfTextFieldComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is NumberFieldComponentEditor -> KfNumberFieldComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is DateFieldComponentEditor -> KfDateTimeFieldComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    format = componentEditor.comp.format,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is MultiSelectComponentEditor -> KfSelectComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is DropdownComponentEditor    -> KfDropComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is ImageComponentEditor       -> KfImageComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onUpload = null,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is SignatureComponentEditor   -> KfSignatureComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is TableComponentEditor       -> KfTableComponent(
                    editor = componentEditor,
                    screen = screen,
                    previewRows = 5,
                    mode = Mode.fill,
                    onUpload = null,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is TextFieldAreaComponentEditor -> KfTextFieldArea(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is ChartComponentEditor       -> KfChartComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is BlockComponentEditor       -> KfBlockComponent(
                    component = componentEditor.comp,
                    position = null,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is RichTextComponentEditor    -> KfRichTextComponent(
                    component = componentEditor.comp,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { select(componentEditor) }
                    ),
                )

                is ColumnComponentEditor      -> KfColumnComponent(
                    editor = componentEditor,
                    screen = screen,
                    selectedComponentId = selectedComponentId,
                    isSelected = selectedComponentId == componentEditor.id,
                    onBlur = onBlur,
                    onFocus = onFocus,
                    onComponentChange = onComponentChange,
                    select = select,
                )

                is RowComponentEditor         -> KfRowComponent(
                    editor = componentEditor,
                    screen = screen,
                    selectedComponentId = selectedComponentId,
                    isSelected = selectedComponentId == componentEditor.id,
                    onBlur = onBlur,
                    onFocus = onFocus,
                    onComponentChange = onComponentChange,
                    select = select,
                )

                else                          -> if (showUnsupportedComponents) {
                    Text("Unsupported Component of type = ${componentEditor.type}")
                }
            }
            separatorComposable()
        }
    }
}

