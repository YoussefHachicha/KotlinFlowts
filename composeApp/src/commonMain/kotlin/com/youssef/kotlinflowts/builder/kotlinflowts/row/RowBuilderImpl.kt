package com.youssef.kotlinflowts.builder.kotlinflowts.row

import com.youssef.kotlinflowts.models.kotlinflowts.ComponentPosition
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component


class RowBuilderImpl(override val identity: IdentityGenerator): RowBuilder {
    val rowComponents = mutableListOf<Component>()

    override fun add(component: Component, position: ComponentPosition) {
        rowComponents.add(component)
        super.add(component, position)
    }
}