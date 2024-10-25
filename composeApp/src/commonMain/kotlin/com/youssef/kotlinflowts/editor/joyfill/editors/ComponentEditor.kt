package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.components.core.Component

interface ComponentEditor {
    var id: String
    var identifier: String
    var title: String
    //    var description: String
    val type: Component.Type

    val component: Component
}