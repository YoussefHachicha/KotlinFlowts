package com.youssef.kotlinflowts.builder.joyfill.column

import com.youssef.kotlinflowts.models.joyfill.ComponentPosition
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.components.core.Component

class ColumnBuilderImpl(override val identity: IdentityGenerator): ColumBuilder {
    val columnComponents = mutableListOf<Component>()

    override fun add(component: Component, position: ComponentPosition) {
        columnComponents.add(component)
    }
}