package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.Mappable
import com.youssef.kotlinflowts.models.kotlinflowts.components.AbstractComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ListBasedComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.JsonList

@PublishedApi
internal abstract class AbstractListBasedComponent<V : Mappable>(
    wrapped: MutableMap<String, Any?>
) : AbstractComponent(wrapped), ListBasedComponent<V> {
    abstract fun factory(map: MutableMap<String, Any?>): V
    override val value: MutableList<V> = run {
        val key = ListBasedComponent<*>::value.name
        val item = wrapped[key]
        if (item == null) {
            wrapped[key] = mutableListOf<MutableMap<String, Any>>()
        }
        return@run JsonList(wrapped[key]) { factory(it) }
    }
}