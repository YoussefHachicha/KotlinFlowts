package com.youssef.kotlinflowts.builder.kotlinflowts.column

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
): ColumBuilder {
    private val _components: MutableStateFlow<List<Component>> = MutableStateFlow(mutableListOf())
    override val components: StateFlow<List<Component>> = _components

    override fun add(component: Component, position: ComponentPosition) {
        _components.update { it + component }
        app.components.add(component)
        println("added column components: ${_components.value.size}")
    }
    override fun addBuilder(wrapped: Pair<String, LayoutBuilder>) {
        app.builders[wrapped.first] = wrapped.second
    }
}