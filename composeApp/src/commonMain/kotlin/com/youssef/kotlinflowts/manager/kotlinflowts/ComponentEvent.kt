package com.youssef.kotlinflowts.manager.kotlinflowts

import com.youssef.kotlinflowts.models.kotlinflowts.Screen
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component

class ComponentEvent(
    val component: Component,
    val screen: Screen
)
