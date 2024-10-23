package com.youssef.kotlinflowts.models.joyfill.fields

import com.youssef.kotlinflowts.models.joyfill.Mappable

sealed interface Field : Mappable {
    val id: String
    val title: String
    val identifier: String
    val type: Type
    val disabled: Boolean

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
        unknown
    }
}