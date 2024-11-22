package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component

interface ComponentEditor {
    var id: String
    var identifier: String
    var title: String
    //    var description: String
    val type: Component.Type

    val comp: Component

    fun isLayout(): Boolean = type == Component.Type.column || type == Component.Type.row
}

