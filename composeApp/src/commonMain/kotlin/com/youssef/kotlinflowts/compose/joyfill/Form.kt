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
import com.youssef.kotlinflowts.manager.joyfill.FieldEvent
import com.youssef.kotlinflowts.manager.joyfill.Mode

@Composable
fun Form(
    editor: AppEditor,
    mode: Mode = Mode.fill,
    onUpload: (suspend (FieldEvent) -> List<String>)? = null,
    pageId: String? = null,
    navigation: Boolean = true,
    onBlur: ((event: FieldEvent) -> Unit)? = null,
    onFocus: ((event: FieldEvent) -> Unit)? = null,
    onFieldChange: ((event: FieldEvent) -> Unit)? = null,
    showUnsupportedFields: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val view = remember(editor) {
        editor.views.find { it.type == "mobile" }
    }

    val pages = remember(editor, view) {
        view?.screens ?: editor.screens.raw()
    }

    var currentPage by remember(pages, pageId) {
        mutableStateOf(pages.find { it.id == pageId } ?: pages.first())
    }

    val fields by remember(editor, currentPage) { derivedStateOf { editor.components.from(currentPage) } }

    fun <T> ComponentEditor.emit(signal: Signal<T>) = when (signal) {
        is Signal.Focus -> onFocus?.invoke(FieldEvent(component, currentPage))
        is Signal.Blur -> onBlur?.invoke(FieldEvent(component, currentPage))
        is Signal.Change -> onFieldChange?.invoke(FieldEvent(component, currentPage))
    }

    LazyColumn(modifier = modifier) {
        if (navigation) item {
            JoyPageSelector(
                screens = pages,
                screen = currentPage,
                onChange = { currentPage = it }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        items(fields, key = { it.id }) { it ->
            when (it) {
                is TextComponentEditor -> JoyTextField(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit,
                )

                is NumberComponentEditor -> JoyNumberField(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is DateComponentEditor -> JoyDateTimeField(
                    editor = it,
                    mode = mode,
                    format = currentPage.positions.firstOrNull { it.componentId == it.id }?.format ?: it.component.format,
                    onSignal = it::emit
                )

                is MultiSelectComponentEditor -> JoySelectField(
                    editor = it,
                    mode = mode,
                    multiple = true,
                    onSignal = it::emit
                )

                is DropdownComponentEditor -> JoyDropField(
                    editor = it,
                    mode = mode,
                    multiple = false,
                    onSignal = it::emit
                )

                is ImageComponentEditor -> JoyImageField(
                    editor = it,
                    mode = mode,
                    onUpload = onUpload?.let { call -> { call(FieldEvent(it.component, currentPage)) } },
                    onSignal = it::emit
                )

                is SignatureComponentEditor -> JoySignatureField(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is TableComponentEditor -> JoyTableField(
                    editor = it,
                    mode = mode,
                    screen = currentPage,
                    onUpload = onUpload,
                    previewRows = 5,
                )

                is TextAreaComponentEditor -> JoyTextArea(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is ChartComponentEditor -> JoyChartField(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is BlockComponentEditor -> JoyBlockField(
                    field = it.component,
                    position = currentPage.positions.find { it.componentId == it.id }
                )

                is RichTextComponentEditor -> JoyRichTextField(it.component)

                else -> if (showUnsupportedFields) {
                    Text("Unsupported Field of type = ${it.type}")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}