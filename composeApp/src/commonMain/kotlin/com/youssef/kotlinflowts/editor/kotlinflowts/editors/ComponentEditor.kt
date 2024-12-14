package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import androidx.compose.ui.graphics.Color
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component

interface ComponentEditor {
    var id: String
    var identifier: String
    var title: String
    val type: Component.Type
    val comp: Component

    fun generateCode(): String {
        return """
            Text(
                text = "${type.name}",
            )
        """.trimIndent()
    }

    var borderColor: Color
    var padding: Int

    fun isLayout(): Boolean = type == Component.Type.column || type == Component.Type.row

    fun changeTitle(title: String) {
        this.title = title
    }


    fun changeBorderColor(color: Color) {
        borderColor = color
    }

}

