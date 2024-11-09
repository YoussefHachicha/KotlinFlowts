package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.TextAreaComponent

interface TextAreaComponentEditor : ValueBasedComponentEditor<String> {
    override val comp: TextAreaComponent
}