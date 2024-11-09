package com.youssef.kotlinflowts.builder.kotlinflowts

import com.youssef.kotlinflowts.models.kotlinflowts.Screen

interface AppBuilder: LayoutBuilder {
    fun name(value: String)

    fun screen(name: String?): Screen
}