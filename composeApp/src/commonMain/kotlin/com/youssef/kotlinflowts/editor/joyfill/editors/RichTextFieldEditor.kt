package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.RichTextField

interface RichTextFieldEditor : FieldEditor {
    override val field: RichTextField
}