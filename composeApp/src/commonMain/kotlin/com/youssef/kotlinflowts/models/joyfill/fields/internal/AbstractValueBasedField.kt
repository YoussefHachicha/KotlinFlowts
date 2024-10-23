package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.AbstractField
import com.youssef.kotlinflowts.models.joyfill.fields.ValueBasedField

@PublishedApi
internal abstract class AbstractValueBasedField<V>(
    wrapped: MutableMap<String, Any?>
) : AbstractField(wrapped), ValueBasedField<V> {
    override var value: V?
        get() = wrapped[ValueBasedField<*>::value.name] as? V
        set(value) {
            wrapped[ValueBasedField<*>::value.name] = value
        }
}