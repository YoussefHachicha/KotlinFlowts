package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.BlockField


interface BlockFieldEditor : FieldEditor {
    override val field: BlockField
}