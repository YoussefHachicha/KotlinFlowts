package com.youssef.kotlinflowts.models.joyfill.components.internal

import com.youssef.kotlinflowts.models.joyfill.components.ColumnComponent
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.toComponent

@PublishedApi
internal class ColumnComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractListBasedComponent<Component>(wrapped), ColumnComponent {
    override fun factory(map: MutableMap<String, Any?>): Component = map.toComponent()
}