package com.youssef.kotlinflowts.models.joyfill.components.table

import com.youssef.kotlinflowts.models.joyfill.Mappable

interface Row : Mappable {
    val id: String
    val deleted: Boolean
    val cells: MutableMap<String, Any?>
}