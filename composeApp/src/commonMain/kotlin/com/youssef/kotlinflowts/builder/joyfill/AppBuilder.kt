package com.youssef.kotlinflowts.builder.joyfill

import com.youssef.kotlinflowts.models.joyfill.Screen

interface AppBuilder: LayoutBuilder {
    fun name(value: String)

    fun screen(name: String?): Screen
}