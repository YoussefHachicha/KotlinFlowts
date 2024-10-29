package com.youssef.kotlinflowts.compose.joyfill

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.joyfill.editors.BlockComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ChartComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.DateComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.AppEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.DropdownComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ComponentEditor
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

@Composable
fun Form(
    editor: AppEditor,
    mode: Mode = Mode.fill,
    onUpload: (suspend (ComponentEvent) -> List<String>)? = null,
    screenId: String? = null,
    navigation: Boolean = true,
    onBlur: ((event: ComponentEvent) -> Unit)? = null,
    onFocus: ((event: ComponentEvent) -> Unit)? = null,
    onComponentChange: ((event: ComponentEvent) -> Unit)? = null,
    showUnsupportedComponents: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val view = remember(editor) {
        editor.views.find { it.type == "mobile" }
    }

    val screens = remember(editor, view) {
        view?.screens ?: editor.screens.raw()
    }

    var currentScreen by remember(screens, screenId) {
        mutableStateOf(screens.find { it.id == screenId } ?: screens.first())
    }

    val editorComponents by remember(editor, currentScreen) { derivedStateOf { editor.components.from(currentScreen) } }

    fun <T> ComponentEditor.emit(signal: Signal<T>) = when (signal) {
        is Signal.Focus -> onFocus?.invoke(ComponentEvent(component, currentScreen))
        is Signal.Blur -> onBlur?.invoke(ComponentEvent(component, currentScreen))
        is Signal.Change -> onComponentChange?.invoke(ComponentEvent(component, currentScreen))
    }

    LazyColumn(modifier = modifier) {
        if (navigation) item {
            JoyScreenSelector(
                screens = screens,
                screen = currentScreen,
                onChange = { currentScreen = it }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        items(editorComponents, key = { it.id }) { it ->
            when (it) {
                is TextComponentEditor -> JoyTextComponent(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit,
                )

                is NumberComponentEditor -> JoyNumberComponent(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is DateComponentEditor -> JoyDateTimeComponent(
                    editor = it,
                    mode = mode,
                    format = currentScreen.positions.firstOrNull { it.componentId == it.id }?.format ?: it.component.format,
                    onSignal = it::emit
                )

                is MultiSelectComponentEditor -> JoySelectComponent(
                    editor = it,
                    mode = mode,
                    multiple = true,
                    onSignal = it::emit
                )

                is DropdownComponentEditor -> JoyDropComponent(
                    editor = it,
                    mode = mode,
                    multiple = false,
                    onSignal = it::emit
                )

                is ImageComponentEditor -> JoyImageComponent(
                    editor = it,
                    mode = mode,
                    onUpload = onUpload?.let { call -> { call(ComponentEvent(it.component, currentScreen)) } },
                    onSignal = it::emit
                )

                is SignatureComponentEditor -> JoySignatureComponent(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is TableComponentEditor -> JoyTableComponent(
                    editor = it,
                    mode = mode,
                    screen = currentScreen,
                    onUpload = onUpload,
                    previewRows = 5,
                )

                is TextAreaComponentEditor -> JoyTextArea(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is ChartComponentEditor -> JoyChartComponent(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is BlockComponentEditor -> JoyBlockComponent(
                    component = it.component,
                    position = currentScreen.positions.find { it.componentId == it.id }
                )

                is RichTextComponentEditor -> JoyRichTextComponent(it.component)

                else -> if (showUnsupportedComponents) {
                    Text("Unsupported Component of type = ${it.type}")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}