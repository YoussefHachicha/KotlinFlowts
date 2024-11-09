package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.ColumnComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.toComponent

@PublishedApi
internal class ColumnComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractListBasedComponent<Component>(wrapped), ColumnComponent {
    override fun factory(map: MutableMap<String, Any?>): Component = map.toComponent()
}