package com.youssef.kotlinflowts.manager.kotlinflowts

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.models.kotlinflowts.Screen

class ComponentEvent(
    val componentEditor: ComponentEditor,
    val screen: Screen
)
