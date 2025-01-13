package com.youssef.kotlinflowts.builder.kotlinflowts

import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.MutableApp
import com.youssef.kotlinflowts.models.kotlinflowts.MutableScreen
import com.youssef.kotlinflowts.models.kotlinflowts.Screen

interface AppBuilder : LayoutBuilder {
    fun name(value: String)

    fun screen(name: String?): Screen

    fun updateCursor(screen: MutableScreen)

    override val app: MutableApp

    val addCompUpdate: Int
}