package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.TextField

interface TextFieldEditor : ValueBasedFieldEditor<String> {
    override val field: TextField
}