package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.TextFieldEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.TextField
import com.youssef.kotlinflowts.models.joyfill.utils.Document

@PublishedApi
internal class TextFieldEditorImpl(
    document: Document,
    override val field: TextField,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedFieldEditor<String,TextField>(document,field,onChange), TextFieldEditor