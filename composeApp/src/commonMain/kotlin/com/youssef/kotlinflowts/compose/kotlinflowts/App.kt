package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.BlockComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ChartComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DateFieldComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.AppEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.column.ColumnComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DropdownComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
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
fun App(
    editor: AppEditor,
    updateUi: Int,
    mode: Mode = Mode.fill,
    onUpload: (suspend (ComponentEvent) -> List<String>)? = null,
    currentScreen: Screen,
    onBlur: ((event: ComponentEvent) -> Unit)? = null,
    onFocus: ((event: ComponentEvent) -> Unit)? = null,
    onComponentChange: ((event: ComponentEvent) -> Unit)? = null,
    showUnsupportedComponents: Boolean = false,
    modifier: Modifier = Modifier,
    onChangeScreen: (Screen) -> Unit,
) {

    LaunchedEffect(currentScreen) {
        onChangeScreen(currentScreen)
        editor.selectedEditorComponent = null
    }

    val editorComponents by remember(
        editor,
        currentScreen,
        updateUi
    ) {
        mutableStateOf(editor.components.from(currentScreen))
    }

    fun <T> ComponentEditor.emit(signal: Signal<T>) = when (signal) {
        is Signal.Focus  -> onFocus?.invoke(ComponentEvent(this, currentScreen))
        is Signal.Blur   -> onBlur?.invoke(ComponentEvent(this, currentScreen))
        is Signal.Change -> onComponentChange?.invoke(ComponentEvent(this, currentScreen))
    }

    LazyColumn(modifier = modifier) {
        items(editorComponents, key = { it.id }) { it ->
            val isSelected by remember(editor.selectedEditorComponent?.id) { mutableStateOf(editor.selectedEditorComponent?.id == it.id) }

            when (it) {
                is TextComponentEditor -> KfTextComponent(
                    editor = it,
                    onSignal = it::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { editor.selectedEditorComponent = it }
                    ),
                )

                is TextFieldComponentEditor -> KfTextFieldComponent(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { editor.selectedEditorComponent = it }
                    ),
                )

                is NumberFieldComponentEditor -> KfNumberFieldComponent(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { editor.selectedEditorComponent = it }
                    ),
                )

                is DateFieldComponentEditor -> KfDateTimeFieldComponent(
                    editor = it,
                    mode = mode,
                    format = currentScreen.positions.firstOrNull { it.componentId == it.id }?.format
                        ?: it.comp.format,
                    onSignal = it::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { editor.selectedEditorComponent = it }
                    ),
                )

                is MultiSelectComponentEditor -> KfSelectComponent(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { editor.selectedEditorComponent = it }
                    ),
                )

                is DropdownComponentEditor    -> KfDropComponent(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { editor.selectedEditorComponent = it }
                    ),
                )

                is ImageComponentEditor       -> KfImageComponent(
                    editor = it,
                    mode = mode,
                    onUpload = onUpload?.let { call ->
                        {
                            call(
                                ComponentEvent(
                                    it,
                                    currentScreen
                                )
                            )
                        }
                    },
                    onSignal = it::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { editor.selectedEditorComponent = it }
                    ),
                )

                is SignatureComponentEditor   -> KfSignatureComponent(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { editor.selectedEditorComponent = it }
                    ),
                )

                is TableComponentEditor       -> KfTableComponent(
                    editor = it,
                    mode = mode,
                    screen = currentScreen,
                    onUpload = onUpload,
                    previewRows = 5,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { editor.selectedEditorComponent = it }
                    ),
                )

                is TextFieldAreaComponentEditor -> KfTextFieldArea(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { editor.selectedEditorComponent = it }
                    ),
                )

                is ChartComponentEditor       -> KfChartComponent(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { editor.selectedEditorComponent = it }
                    ),
                )

                is BlockComponentEditor       -> KfBlockComponent(
                    component = it.comp,
                    position = currentScreen.positions.find { it.componentId == it.id },
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { editor.selectedEditorComponent = it }
                    ),
                )

                is RichTextComponentEditor    -> KfRichTextComponent(
                    component = it.comp,
                    modifier = Modifier.hoverSelect(
                        isSelected = isSelected,
                        onSelect = { editor.selectedEditorComponent = it }
                    ),
                )

                is ColumnComponentEditor      -> KfColumnComponent(
                    editor = it,
                    screen = currentScreen,
                    selectedComponentId = editor.selectedEditorComponent?.id ?: "",
                    isSelected = editor.selectedEditorComponent?.id == it.id,
                    onBlur = onBlur,
                    onFocus = onFocus,
                    onComponentChange = onComponentChange,
                    showUnsupportedComponents = showUnsupportedComponents,
                ) {
                    editor.selectedEditorComponent = it
                }

                is RowComponentEditor         -> KfRowComponent(
                    editor = it,
                    screen = currentScreen,
                    selectedComponentId = editor.selectedEditorComponent?.id ?: "",
                    isSelected = editor.selectedEditorComponent?.id == it.id,
                    onBlur = onBlur,
                    onFocus = onFocus,
                    onComponentChange = onComponentChange,
                    showUnsupportedComponents = showUnsupportedComponents,
                ) {
                    editor.selectedEditorComponent = it
                }

                else                          -> if (showUnsupportedComponents) {
                    Text("Unsupported Component of type = ${it.type}")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}