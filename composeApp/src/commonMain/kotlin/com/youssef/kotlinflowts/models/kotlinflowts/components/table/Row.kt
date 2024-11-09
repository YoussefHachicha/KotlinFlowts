package com.youssef.kotlinflowts.models.kotlinflowts.components.table

import com.youssef.kotlinflowts.models.kotlinflowts.Mappable

interface Row : Mappable {
    val id: String
    val deleted: Boolean
    val cells: MutableMap<String, Any?>
}