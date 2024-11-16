package com.youssef.kotlinflowts.builder.kotlinflowts.column

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.builder.kotlinflowts.LayoutBuilder
import com.youssef.kotlinflowts.models.kotlinflowts.ComponentPosition
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component

class ColumnBuilderImpl(override val identity: IdentityGenerator): ColumBuilder {
    override val components: MutableList<Component> = mutableListOf()
    override var updateUi by mutableStateOf(0)

    override fun add(component: Component, position: ComponentPosition) {
        components.add(component)
        println("added column components: ${components.size}")
        updateUi++
    }
}