package com.youssef.kotlinflowts.builder.kotlinflowts.column

import androidx.compose.runtime.mutableStateListOf
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
    private val _components = mutableStateListOf<Component>()
    override val components: List<Component> = _components

    override fun add(component: Component, position: ComponentPosition) {
        _components.add(component)
        app.components.add(component)
    }

    override fun delete(id: String) {
        _components.removeIf { it.id == id }
        app.components.removeIf { it.id == id }
    }
}