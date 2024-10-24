package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.FileField

interface FileFieldEditor : FileBasedFieldEditor {
    override val field: FileField
}