package com.youssef.kotlinflowts.models.kotlinflowts.components.core

import com.youssef.kotlinflowts.models.kotlinflowts.Mappable

interface Component : Mappable {
    val id: String
    val title: String
    val identifier: String
    val type: Type
    val disabled: Boolean
    val depth: Int
    val builderId: String

    enum class Type {
        text,
        textField,
        textFieldArea,
        numberField,
        dropdown,
        multiSelect,
        dateField,
        richText,
        signature,
        table,
        chart,
        image,
        file,
        block,
        column,
        row,
        unknown
    }
}

