package com.youssef.kotlinflowts.builder.joyfill.row

import com.youssef.kotlinflowts.models.joyfill.ComponentPosition
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.components.core.Component


class RowBuilderImpl(override val identity: IdentityGenerator): RowBuilder {
    val rowComponents = mutableListOf<Component>()

    override fun add(component: Component, position: ComponentPosition) {
        rowComponents.add(component)
    }
}