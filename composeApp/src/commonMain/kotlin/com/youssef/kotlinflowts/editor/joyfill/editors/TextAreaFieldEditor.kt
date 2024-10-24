package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.TextAreaField

interface TextAreaFieldEditor : ValueBasedFieldEditor<String> {
    override val field: TextAreaField
}