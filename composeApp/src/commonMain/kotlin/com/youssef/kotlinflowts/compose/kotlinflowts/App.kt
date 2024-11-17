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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.BlockComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ChartComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DateComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.AppEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.column.ColumnComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DropdownComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
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

@Composable
fun App(
    editor: AppEditor,
    updateUi: Int,
    mode: Mode = Mode.fill,
    onUpload: (suspend (ComponentEvent) -> List<String>)? = null,
    screenId: String? = null,
    navigation: Boolean = true,
    onBlur: ((event: ComponentEvent) -> Unit)? = null,
    onFocus: ((event: ComponentEvent) -> Unit)? = null,
    onComponentChange: ((event: ComponentEvent) -> Unit)? = null,
    showUnsupportedComponents: Boolean = false,
    modifier: Modifier = Modifier,
    onChangeScreen: (Screen) -> Unit,
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

    LaunchedEffect(currentScreen){
        onChangeScreen(currentScreen)
    }

    val editorComponents by remember(
        editor,
        currentScreen,
        updateUi
    ) { mutableStateOf(editor.components.from(currentScreen))  }

    fun <T> ComponentEditor.emit(signal: Signal<T>) = when (signal) {
        is Signal.Focus -> onFocus?.invoke(ComponentEvent(comp, currentScreen))
        is Signal.Blur -> onBlur?.invoke(ComponentEvent(comp, currentScreen))
        is Signal.Change -> onComponentChange?.invoke(ComponentEvent(comp, currentScreen))
    }

    LazyColumn(modifier = modifier) {
        if (navigation) item {
            KfScreenSelector(
                screens = screens,
                screen = currentScreen,
                onChange = {
                    currentScreen = it
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        items(editorComponents, key = { it.id }) { it ->
            when (it) {
                is TextComponentEditor -> KfTextComponent(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit,
                )

                is NumberComponentEditor -> KfNumberComponent(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is DateComponentEditor -> KfDateTimeComponent(
                    editor = it,
                    mode = mode,
                    format = currentScreen.positions.firstOrNull { it.componentId == it.id }?.format
                        ?: it.comp.format,
                    onSignal = it::emit
                )

                is MultiSelectComponentEditor -> KfSelectComponent(
                    editor = it,
                    mode = mode,
                    multiple = true,
                    onSignal = it::emit
                )

                is DropdownComponentEditor -> KfDropComponent(
                    editor = it,
                    mode = mode,
                    multiple = false,
                    onSignal = it::emit
                )

                is ImageComponentEditor -> KfImageComponent(
                    editor = it,
                    mode = mode,
                    onUpload = onUpload?.let { call ->
                        {
                            call(
                                ComponentEvent(
                                    it.comp,
                                    currentScreen
                                )
                            )
                        }
                    },
                    onSignal = it::emit
                )

                is SignatureComponentEditor -> KfSignatureComponent(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is TableComponentEditor -> KfTableComponent(
                    editor = it,
                    mode = mode,
                    screen = currentScreen,
                    onUpload = onUpload,
                    previewRows = 5,
                )

                is TextAreaComponentEditor -> KfTextArea(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is ChartComponentEditor -> KfChartComponent(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is BlockComponentEditor -> KfBlockComponent(
                    component = it.comp,
                    position = currentScreen.positions.find { it.componentId == it.id }
                )

                is RichTextComponentEditor -> KfRichTextComponent(it.comp)

                is ColumnComponentEditor -> KfColumnComponent(
                    editor = it,
                    screen = currentScreen,
                    onBlur = onBlur,
                    onFocus = onFocus,
                    onComponentChange = onComponentChange,
                    showUnsupportedComponents = showUnsupportedComponents,
                )

                is RowComponentEditor -> KfRowComponent(
                    editor = it,
                    screen = currentScreen,
                    onBlur = onBlur,
                    onFocus = onFocus,
                    onComponentChange = onComponentChange,
                    showUnsupportedComponents = showUnsupportedComponents,
                )

                else -> if (showUnsupportedComponents) {
                    Text("Unsupported Component of type = ${it.type}")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}