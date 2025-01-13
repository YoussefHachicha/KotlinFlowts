package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.TextFieldAreaComponent

interface TextFieldAreaComponentEditor : ValueBasedComponentEditor<String> {
    override val comp: TextFieldAreaComponent
}