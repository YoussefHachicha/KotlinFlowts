package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.ImageField

interface ImageFieldEditor : FileBasedFieldEditor {
    override val field: ImageField
}