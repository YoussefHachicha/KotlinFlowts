package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.components.TextAreaComponent

interface TextAreaComponentEditor : ValueBasedComponentEditor<String> {
    override val comp: TextAreaComponent
}