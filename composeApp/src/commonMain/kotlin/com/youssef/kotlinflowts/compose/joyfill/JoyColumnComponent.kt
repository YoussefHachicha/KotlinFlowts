package com.youssef.kotlinflowts.compose.joyfill

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
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
import com.youssef.kotlinflowts.manager.joyfill.ComponentEvent
import com.youssef.kotlinflowts.manager.joyfill.Mode
import com.youssef.kotlinflowts.models.joyfill.Screen

@Composable
internal fun JoyColumnComponent(
    editor: ColumnComponentEditor,
    screen: Screen,
    onBlur: ((event: ComponentEvent) -> Unit)? = null,
    onFocus: ((event: ComponentEvent) -> Unit)? = null,
    onComponentChange: ((event: ComponentEvent) -> Unit)? = null,
    showUnsupportedComponents: Boolean = false,
) {
    val component = remember(editor) { editor.comp }
    val columnComponents = remember(editor) { editor.columnComponents.all() }

    fun <T> ComponentEditor.emit(signal: Signal<T>) = when (signal) {
        is Signal.Focus -> onFocus?.invoke(ComponentEvent(component, screen))
        is Signal.Blur -> onBlur?.invoke(ComponentEvent(component, screen))
        is Signal.Change -> onComponentChange?.invoke(ComponentEvent(component, screen))
    }

    Column(
        modifier = Modifier
            .testTag(editor.comp.id)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        JoyTitle(component.title, modifier = Modifier.testTag("${component.id}-title"))
        Spacer(modifier = Modifier.height(2.dp))

        columnComponents.forEach { componentEditor ->
            key(componentEditor.id) {  // Use key for stable identity
                when (componentEditor) {
                    is TextComponentEditor -> JoyTextComponent(
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
                    else -> if (showUnsupportedComponents) {
                        Text("Unsupported Component of type = ${componentEditor.type}")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
