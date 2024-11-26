package com.youssef.kotlinflowts.builder.kotlinflowts.internal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.builder.kotlinflowts.AppBuilder
import com.youssef.kotlinflowts.builder.kotlinflowts.file
import com.youssef.kotlinflowts.builder.kotlinflowts.screen
import com.youssef.kotlinflowts.models.kotlinflowts.ComponentPosition
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.MutableApp
import com.youssef.kotlinflowts.models.kotlinflowts.MutableScreen
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AppBuilderImpl(
    override val app: MutableApp,
    override val identity: IdentityGenerator
) : AppBuilder {
    override var addCompUpdate by mutableStateOf(0)
    var addScreenUpdate by mutableStateOf(0)

    private val _components: MutableStateFlow<List<Component>> = MutableStateFlow(mutableListOf())
    override val components: StateFlow<List<Component>> = _components.asStateFlow()
    override val depth: Int = 1
    override val builderId: String = "mainBuilder"


    //the file will contain the code source of our app
    private val file by lazy {
        app.files.getOrNull(0) ?: file(
            id = identity.generate(),
            name = app.name,
            screens = mutableListOf(),
            screenOrder = mutableListOf()
        ).also { app.files.add(it) }
    }



    override fun name(value: String) {
        app.name = value
        file.name = value
    }

    private fun cursor(): MutableScreen {
        return app.cursor ?: screen("New Screen")
    }

    override fun updateCursor(screen: MutableScreen) {
        app.cursor = screen
    }

    override fun screen(name: String?): MutableScreen {
        val s = screen(
            id = identity.generate(),
            name = name ?: "Screen ${file.screens.size + 1}",
            positions = mutableListOf()
        )
        app.cursor = s
        file.screens.add(s)
        file.screenOrder.add(s.id)
        addScreenUpdate++
        return s
    }

    override fun add(component: Component, position: ComponentPosition) {
        cursor().positions.add(position)
        app.components.add(component)
        _components.update { it + component }
        addCompUpdate++
    }

    override fun delete(id: String) {
        cursor().positions.removeIf { it.componentId == id }
        app.components.removeIf { it.id == id }
        _components.update { it.filter { it.id != id } }
        addCompUpdate++
    }
}