package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.Mappable
import com.youssef.kotlinflowts.models.joyfill.fields.AbstractField
import com.youssef.kotlinflowts.models.joyfill.fields.ListBasedField
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList

@PublishedApi
internal abstract class AbstractListBasedField<V : Mappable>(
    wrapped: MutableMap<String, Any?>
) : AbstractField(wrapped), ListBasedField<V> {
    abstract fun factory(map: MutableMap<String, Any?>): V
    override val value: MutableList<V> = run {
        val key = ListBasedField<*>::value.name
        val item = wrapped[key]
        if (item == null) {
            wrapped[key] = mutableListOf<MutableMap<String, Any>>()
        }
        return@run JsonList(wrapped[key]) { factory(it) }
    }
}