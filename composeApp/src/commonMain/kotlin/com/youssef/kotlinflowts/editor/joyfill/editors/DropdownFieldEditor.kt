package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.DropdownField

interface DropdownFieldEditor : FieldEditor, DropdownEditor {
    override val field: DropdownField
}