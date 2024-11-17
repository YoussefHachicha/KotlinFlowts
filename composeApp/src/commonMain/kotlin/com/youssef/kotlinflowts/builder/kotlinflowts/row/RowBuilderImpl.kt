package com.youssef.kotlinflowts.builder.kotlinflowts.row

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.models.kotlinflowts.ComponentPosition
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class RowBuilderImpl(override val identity: IdentityGenerator) : RowBuilder {
    private val _components: MutableStateFlow<List<Component>> = MutableStateFlow(mutableListOf())
    override val components: StateFlow<List<Component>> = _components

    override var updateUi by mutableStateOf(0)

    override fun add(component: Component, position: ComponentPosition) {
        _components.update { it + component }
        println("added row components: ${components.value.size}")
        updateUi++
    }
}