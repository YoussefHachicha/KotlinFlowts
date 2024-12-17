package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.TextFieldComponent

interface TextFieldComponentEditor : ValueBasedComponentEditor<String> {
    override val comp: TextFieldComponent
}