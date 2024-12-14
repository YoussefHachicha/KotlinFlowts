package com.youssef.kotlinflowts.builder.kotlinflowts.column

import com.youssef.kotlinflowts.builder.kotlinflowts.LayoutBuilder
import com.youssef.kotlinflowts.models.kotlinflowts.ComponentPosition
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.MutableApp
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ColumnBuilderImpl(
    override val identity: IdentityGenerator,
    override val app: MutableApp,
    override val depth: Int,
    override val builderId: String,
): ColumBuilder {
    private val _components: MutableStateFlow<List<Component>> = MutableStateFlow(mutableListOf())
    override val components: StateFlow<List<Component>> = _components

    override fun add(component: Component, position: ComponentPosition) {
        _components.update { it + component }
        app.components.add(component)
//        app.cursor?.positions?.add(position)
        app.components
    }

    override fun delete(id: String) {
        _components.update { it.filter { it.id != id } }
        app.components.removeIf { it.id == id }
    }
}