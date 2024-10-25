package com.youssef.kotlinflowts.models.joyfill.fields.table

import com.youssef.kotlinflowts.models.joyfill.Mappable
import com.youssef.kotlinflowts.models.joyfill.fields.Component

interface Column : Mappable {
    val id: String
    val title: String
    val type: Component.Type
}
