package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.TextAreaComponent

interface TextAreaComponentEditor : ValueBasedComponentEditor<String> {
    override val component: TextAreaComponent
}