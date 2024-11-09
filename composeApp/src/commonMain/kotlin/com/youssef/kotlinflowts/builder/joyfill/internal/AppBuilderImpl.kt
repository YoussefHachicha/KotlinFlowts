package com.youssef.kotlinflowts.builder.joyfill.internal

import com.youssef.kotlinflowts.builder.joyfill.AppBuilder
import com.youssef.kotlinflowts.builder.joyfill.file
import com.youssef.kotlinflowts.builder.joyfill.screen
import com.youssef.kotlinflowts.models.joyfill.ComponentPosition
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.MutableApp
import com.youssef.kotlinflowts.models.joyfill.MutableScreen
import com.youssef.kotlinflowts.models.joyfill.components.core.Component

class AppBuilderImpl(
    internal val app: MutableApp,
    override val identity: IdentityGenerator
) : AppBuilder {
    //the file will contain the code source of our app
    private val file by lazy {
        app.files.getOrNull(0) ?: file(
            id = identity.generate(),
            name = app.name,
            screens = mutableListOf(),
            screenOrder = mutableListOf()
        ).also { app.files.add(it) }
    }

    private var cursor: MutableScreen? = null

    override fun name(value: String) {
        app.name = value
        file.name = value
    }

    private fun cursor(): MutableScreen {
        return cursor ?: screen("New Screen")
    }

    override fun screen(name: String?): MutableScreen {
        val s = screen(
            id = identity.generate(),
            name = name ?: "Screen ${file.screens.size + 1}",
            positions = mutableListOf()
        )
        cursor = s
        file.screens.add(s)
        file.screenOrder.add(s.id)
        return s
    }

    override fun add(component: Component, position: ComponentPosition) {
        cursor().positions.add(position)
        app.components.add(component)
    }
}