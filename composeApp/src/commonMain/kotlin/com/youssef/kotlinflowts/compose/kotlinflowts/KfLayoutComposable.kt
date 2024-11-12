package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import com.youssef.kotlinflowts.editor.kotlinflowts.column.ColumnComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.BlockComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ChartComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DateComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DropdownComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ImageComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.MultiSelectComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.NumberComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.RichTextComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.SignatureComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TableComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextAreaComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.row.RowComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.ComponentEvent
import com.youssef.kotlinflowts.manager.kotlinflowts.Mode
import com.youssef.kotlinflowts.models.kotlinflowts.Screen
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component

@Composable
internal fun RowScope.KfLayoutComposable (
    component: Component,
    screen: Screen,
    componentEditors: List<ComponentEditor>,
    onBlur: ((event: ComponentEvent) -> Unit)? = null,
    onFocus: ((event: ComponentEvent) -> Unit)? = null,
    onComponentChange: ((event: ComponentEvent) -> Unit)? = null,
    showUnsupportedComponents: Boolean = false,
    separatorComposable: @Composable () -> Unit
){
    fun <T> ComponentEditor.emit(signal: Signal<T>) = when (signal) {
        is Signal.Focus -> onFocus?.invoke(ComponentEvent(component, screen))
        is Signal.Blur -> onBlur?.invoke(ComponentEvent(component, screen))
        is Signal.Change -> onComponentChange?.invoke(ComponentEvent(component, screen))
    }
    componentEditors.forEach { componentEditor ->
        key(componentEditor.id) {  // Use key for stable identity
            when (componentEditor) {
                is TextComponentEditor -> KfTextComponent(
                    editor = componentEditor,
                    onSignal = componentEditor::emit
                )
                is NumberComponentEditor -> KfNumberComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is DateComponentEditor -> KfDateTimeComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    format = componentEditor.comp.format,
                    onSignal = componentEditor::emit
                )
                is MultiSelectComponentEditor -> KfSelectComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    multiple = true,
                    onSignal = componentEditor::emit
                )
                is DropdownComponentEditor -> KfDropComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    multiple = false,
                    onSignal = componentEditor::emit
                )
                is ImageComponentEditor -> KfImageComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onUpload = null,
                    onSignal = componentEditor::emit
                )
                is SignatureComponentEditor -> KfSignatureComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is TableComponentEditor -> KfTableComponent(
                    editor = componentEditor,
                    screen = screen,
                    previewRows = 5,
                    mode = Mode.fill,
                    onUpload = null
                )
                is TextAreaComponentEditor -> KfTextArea(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is ChartComponentEditor -> KfChartComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is BlockComponentEditor -> KfBlockComponent(
                    component = componentEditor.comp,
                    position = null
                )
                is RichTextComponentEditor -> KfRichTextComponent(
                    componentEditor.comp
                )
                is ColumnComponentEditor -> KfColumnComponent(
                    editor = componentEditor,
                    screen = screen,
                    onFocus = onFocus,
                    onBlur = onBlur,
                    onComponentChange = onComponentChange,
                )
                is RowComponentEditor -> KfRowComponent(
                    editor = componentEditor,
                    screen = screen,
                    onFocus = onFocus,
                    onBlur = onBlur,
                    onComponentChange = onComponentChange,
                )
                else -> if (showUnsupportedComponents) {
                    Text("Unsupported Component of type = ${componentEditor.type}")
                }
            }
            separatorComposable()
        }
    }
}


@Composable
internal fun ColumnScope.KfLayoutComposable (
    component: Component,
    screen: Screen,
    componentEditors: List<ComponentEditor>,
    onBlur: ((event: ComponentEvent) -> Unit)? = null,
    onFocus: ((event: ComponentEvent) -> Unit)? = null,
    onComponentChange: ((event: ComponentEvent) -> Unit)? = null,
    showUnsupportedComponents: Boolean = false,
    separatorComposable: @Composable () -> Unit
){
    fun <T> ComponentEditor.emit(signal: Signal<T>) = when (signal) {
        is Signal.Focus -> onFocus?.invoke(ComponentEvent(component, screen))
        is Signal.Blur -> onBlur?.invoke(ComponentEvent(component, screen))
        is Signal.Change -> onComponentChange?.invoke(ComponentEvent(component, screen))
    }
    componentEditors.forEach { componentEditor ->
        key(componentEditor.id) {  // Use key for stable identity
            when (componentEditor) {
                is TextComponentEditor -> KfTextComponent(
                    editor = componentEditor,
                    onSignal = componentEditor::emit
                )
                is NumberComponentEditor -> KfNumberComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is DateComponentEditor -> KfDateTimeComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    format = componentEditor.comp.format,
                    onSignal = componentEditor::emit
                )
                is MultiSelectComponentEditor -> KfSelectComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    multiple = true,
                    onSignal = componentEditor::emit
                )
                is DropdownComponentEditor -> KfDropComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    multiple = false,
                    onSignal = componentEditor::emit
                )
                is ImageComponentEditor -> KfImageComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onUpload = null,
                    onSignal = componentEditor::emit
                )
                is SignatureComponentEditor -> KfSignatureComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is TableComponentEditor -> KfTableComponent(
                    editor = componentEditor,
                    screen = screen,
                    previewRows = 5,
                    mode = Mode.fill,
                    onUpload = null
                )
                is TextAreaComponentEditor -> KfTextArea(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is ChartComponentEditor -> KfChartComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is BlockComponentEditor -> KfBlockComponent(
                    component = componentEditor.comp,
                    position = null
                )
                is RichTextComponentEditor -> KfRichTextComponent(
                    componentEditor.comp
                )
                is ColumnComponentEditor -> KfColumnComponent(
                    editor = componentEditor,
                    screen = screen,
                    onFocus = onFocus,
                    onBlur = onBlur,
                    onComponentChange = onComponentChange,
                )
                is RowComponentEditor -> KfRowComponent(
                    editor = componentEditor,
                    screen = screen,
                    onFocus = onFocus,
                    onBlur = onBlur,
                    onComponentChange = onComponentChange,
                )
                else -> if (showUnsupportedComponents) {
                    Text("Unsupported Component of type = ${componentEditor.type}")
                }
            }
            separatorComposable()
        }
    }
}

