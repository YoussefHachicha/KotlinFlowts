package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.TextAreaFieldEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.TextAreaField
import com.youssef.kotlinflowts.models.joyfill.utils.Document

@PublishedApi
internal class TextAreaFieldEditorImpl(
    document: Document,
    override val field: TextAreaField,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedFieldEditor<String,TextAreaField>(document,field,onChange), TextAreaFieldEditor