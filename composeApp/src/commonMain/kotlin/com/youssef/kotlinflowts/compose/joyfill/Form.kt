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
import com.youssef.kotlinflowts.editor.joyfill.editors.BlockFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ChartFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.DateFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.DocumentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.DropdownFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.FieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ImageFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.MultiSelectFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.NumberFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.RichTextFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.SignatureFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TableFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TextAreaFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TextFieldEditor
import com.youssef.kotlinflowts.manager.joyfill.FieldEvent
import com.youssef.kotlinflowts.manager.joyfill.Mode

@Composable
fun Form(
    editor: DocumentEditor,
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
        view?.pages ?: editor.pages.raw()
    }

    var currentPage by remember(pages, pageId) {
        mutableStateOf(pages.find { it.id == pageId } ?: pages.first())
    }

    val fields by remember(editor, currentPage) { derivedStateOf { editor.fields.from(currentPage) } }

    fun <T> FieldEditor.emit(signal: Signal<T>) = when (signal) {
        is Signal.Focus -> onFocus?.invoke(FieldEvent(field, currentPage))
        is Signal.Blur -> onBlur?.invoke(FieldEvent(field, currentPage))
        is Signal.Change -> onFieldChange?.invoke(FieldEvent(field, currentPage))
    }

    LazyColumn(modifier = modifier) {
        if (navigation) item {
            JoyPageSelector(
                pages = pages,
                page = currentPage,
                onChange = { currentPage = it }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        items(fields, key = { it.id }) { it ->
            when (it) {
                is TextFieldEditor -> JoyTextField(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit,
                )

                is NumberFieldEditor -> JoyNumberField(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is DateFieldEditor -> JoyDateTimeField(
                    editor = it,
                    mode = mode,
                    format = currentPage.positions.firstOrNull { it.field == it.id }?.format ?: it.field.format,
                    onSignal = it::emit
                )

                is MultiSelectFieldEditor -> JoySelectField(
                    editor = it,
                    mode = mode,
                    multiple = true,
                    onSignal = it::emit
                )

                is DropdownFieldEditor -> JoyDropField(
                    editor = it,
                    mode = mode,
                    multiple = false,
                    onSignal = it::emit
                )

                is ImageFieldEditor -> JoyImageField(
                    editor = it,
                    mode = mode,
                    onUpload = onUpload?.let { call -> { call(FieldEvent(it.field, currentPage)) } },
                    onSignal = it::emit
                )

                is SignatureFieldEditor -> JoySignatureField(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is TableFieldEditor -> JoyTableField(
                    editor = it,
                    mode = mode,
                    page = currentPage,
                    onUpload = onUpload,
                    previewRows = 5,
                )

                is TextAreaFieldEditor -> JoyTextArea(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is ChartFieldEditor -> JoyChartField(
                    editor = it,
                    mode = mode,
                    onSignal = it::emit
                )

                is BlockFieldEditor -> JoyBlockField(
                    field = it.field,
                    position = currentPage.positions.find { it.field == it.id }
                )

                is RichTextFieldEditor -> JoyRichTextField(it.field)

                else -> if (showUnsupportedFields) {
                    Text("Unsupported Field of type = ${it.type}")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}