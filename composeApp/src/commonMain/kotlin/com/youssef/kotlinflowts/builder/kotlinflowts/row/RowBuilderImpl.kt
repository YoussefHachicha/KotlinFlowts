package com.youssef.kotlinflowts.builder.kotlinflowts.row

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.builder.kotlinflowts.LayoutBuilder
import com.youssef.kotlinflowts.models.kotlinflowts.ComponentPosition
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component


class RowBuilderImpl(override val identity: IdentityGenerator) : RowBuilder {
    override val components: MutableList<Component> = mutableListOf()
    override var updateUi by mutableStateOf(0)

    override fun add(component: Component, position: ComponentPosition) {
        components.add(component)
        println("added row components: ${components.size}")
        updateUi++
    }
}