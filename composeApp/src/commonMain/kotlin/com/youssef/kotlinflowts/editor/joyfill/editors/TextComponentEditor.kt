package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.TextComponent

interface TextComponentEditor : ValueBasedComponentEditor<String> {
    override val component: TextComponent
}