package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.ValueBasedFieldEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.ValueBasedField
import com.youssef.kotlinflowts.models.joyfill.utils.Document

@PublishedApi
internal abstract class AbstractValueBasedFieldEditor<V, F : ValueBasedField<V>>(
    document: Document,
    override val field: F,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyFieldEditor<F>(document, field, onChange), ValueBasedFieldEditor<V> {
    override var value: V?
        get() = this.field.value
        set(value) {
            this.field.value = value
            notifyChange(value)
        }
}