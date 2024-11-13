package com.youssef.kotlinflowts.builder.kotlinflowts.column

import com.youssef.kotlinflowts.models.kotlinflowts.ComponentPosition
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component

class ColumnBuilderImpl(override val identity: IdentityGenerator): ColumBuilder {
    val columnComponents = mutableListOf<Component>()

    override fun add(component: Component, position: ComponentPosition) {
        columnComponents.add(component)
        super.add(component, position)
    }
}