package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.DateFieldEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.DateField
import com.youssef.kotlinflowts.models.joyfill.utils.Document

@PublishedApi
internal class DateFieldEditorImpl(
    document: Document,
    override val field: DateField,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedFieldEditor<Long, DateField>(document, field, onChange), DateFieldEditor