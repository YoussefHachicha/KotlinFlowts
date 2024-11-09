package com.youssef.kotlinflowts.compose.joyfill

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import com.youssef.kotlinflowts.editor.joyfill.column.ColumnComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.BlockComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ChartComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.DateComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.DropdownComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ImageComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.MultiSelectComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.NumberComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.RichTextComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.SignatureComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TableComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TextAreaComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TextComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.row.RowComponentEditor
import com.youssef.kotlinflowts.manager.joyfill.ComponentEvent
import com.youssef.kotlinflowts.manager.joyfill.Mode
import com.youssef.kotlinflowts.models.joyfill.Screen
import com.youssef.kotlinflowts.models.joyfill.components.core.Component

@Composable
fun RowScope.LayoutComposable (
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
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is NumberComponentEditor -> JoyNumberComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is DateComponentEditor -> JoyDateTimeComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    format = componentEditor.comp.format,
                    onSignal = componentEditor::emit
                )
                is MultiSelectComponentEditor -> JoySelectComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    multiple = true,
                    onSignal = componentEditor::emit
                )
                is DropdownComponentEditor -> JoyDropComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    multiple = false,
                    onSignal = componentEditor::emit
                )
                is ImageComponentEditor -> JoyImageComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onUpload = null,
                    onSignal = componentEditor::emit
                )
                is SignatureComponentEditor -> JoySignatureComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is TableComponentEditor -> JoyTableComponent(
                    editor = componentEditor,
                    screen = screen,
                    previewRows = 5,
                    mode = Mode.fill,
                    onUpload = null
                )
                is TextAreaComponentEditor -> JoyTextArea(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is ChartComponentEditor -> JoyChartComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is BlockComponentEditor -> JoyBlockComponent(
                    component = componentEditor.comp,
                    position = null
                )
                is RichTextComponentEditor -> JoyRichTextComponent(
                    componentEditor.comp
                )
                is ColumnComponentEditor -> JoyColumnComponent(
                    editor = componentEditor,
                    screen = screen,
                    onFocus = onFocus,
                    onBlur = onBlur,
                    onComponentChange = onComponentChange,
                )
                is RowComponentEditor -> JoyRowComponent(
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
fun ColumnScope.LayoutComposable (
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
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is NumberComponentEditor -> JoyNumberComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is DateComponentEditor -> JoyDateTimeComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    format = componentEditor.comp.format,
                    onSignal = componentEditor::emit
                )
                is MultiSelectComponentEditor -> JoySelectComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    multiple = true,
                    onSignal = componentEditor::emit
                )
                is DropdownComponentEditor -> JoyDropComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    multiple = false,
                    onSignal = componentEditor::emit
                )
                is ImageComponentEditor -> JoyImageComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onUpload = null,
                    onSignal = componentEditor::emit
                )
                is SignatureComponentEditor -> JoySignatureComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is TableComponentEditor -> JoyTableComponent(
                    editor = componentEditor,
                    screen = screen,
                    previewRows = 5,
                    mode = Mode.fill,
                    onUpload = null
                )
                is TextAreaComponentEditor -> JoyTextArea(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is ChartComponentEditor -> JoyChartComponent(
                    editor = componentEditor,
                    mode = Mode.fill,
                    onSignal = componentEditor::emit
                )
                is BlockComponentEditor -> JoyBlockComponent(
                    component = componentEditor.comp,
                    position = null
                )
                is RichTextComponentEditor -> JoyRichTextComponent(
                    componentEditor.comp
                )
                is ColumnComponentEditor -> JoyColumnComponent(
                    editor = componentEditor,
                    screen = screen,
                    onFocus = onFocus,
                    onBlur = onBlur,
                    onComponentChange = onComponentChange,
                )
                is RowComponentEditor -> JoyRowComponent(
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

