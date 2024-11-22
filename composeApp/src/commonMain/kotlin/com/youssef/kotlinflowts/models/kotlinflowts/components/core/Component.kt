package com.youssef.kotlinflowts.models.kotlinflowts.components.core

import com.youssef.kotlinflowts.models.kotlinflowts.Mappable

interface Component : Mappable {
    val id: String
    var title: String
    val identifier: String
    val type: Type
    val disabled: Boolean
    val depth: Int

    enum class Type {
        text,
        textarea,
        number,
        dropdown,
        multiSelect,
        date,
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

