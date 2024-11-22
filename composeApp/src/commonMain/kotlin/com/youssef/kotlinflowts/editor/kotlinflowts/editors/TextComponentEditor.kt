package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.TextComponent

interface TextComponentEditor : ValueBasedComponentEditor<String> {
    override val comp: TextComponent

    fun changeTitle(title: String) {
        comp.title = title
    }
}