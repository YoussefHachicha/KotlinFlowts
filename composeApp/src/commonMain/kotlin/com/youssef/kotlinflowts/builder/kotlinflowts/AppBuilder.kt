package com.youssef.kotlinflowts.builder.kotlinflowts

import androidx.compose.runtime.mutableIntStateOf
import com.youssef.kotlinflowts.models.kotlinflowts.Screen

interface AppBuilder: LayoutBuilder {
    fun name(value: String)

    fun screen(name: String?): Screen

}