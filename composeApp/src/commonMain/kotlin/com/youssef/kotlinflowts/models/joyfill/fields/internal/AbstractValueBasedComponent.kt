package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.AbstractComponent
import com.youssef.kotlinflowts.models.joyfill.fields.ValueBasedComponent

@PublishedApi
internal abstract class AbstractValueBasedComponent<V>(
    wrapped: MutableMap<String, Any?>
) : AbstractComponent(wrapped), ValueBasedComponent<V> {
    override var value: V?
        get() = wrapped[ValueBasedComponent<*>::value.name] as? V
        set(value) {
            wrapped[ValueBasedComponent<*>::value.name] = value
        }
}