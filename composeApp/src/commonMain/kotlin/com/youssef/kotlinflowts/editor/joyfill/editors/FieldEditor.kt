package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.Field

interface FieldEditor {
    var id: String
    var identifier: String
    var title: String
    //    var description: String
    val type: Field.Type

    val field: Field
}