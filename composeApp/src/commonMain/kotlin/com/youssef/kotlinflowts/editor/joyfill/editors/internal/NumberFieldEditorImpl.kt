package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.NumberFieldEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.NumberField
import com.youssef.kotlinflowts.models.joyfill.utils.Document

@PublishedApi
internal class NumberFieldEditorImpl(
    document: Document,
    override val field: NumberField,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedFieldEditor<Double, NumberField>(document, field, onChange), NumberFieldEditor