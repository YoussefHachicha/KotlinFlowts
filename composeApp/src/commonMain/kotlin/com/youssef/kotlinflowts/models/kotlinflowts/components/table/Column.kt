package com.youssef.kotlinflowts.models.kotlinflowts.components.table

import com.youssef.kotlinflowts.models.kotlinflowts.Mappable
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component

interface Column : Mappable {
    val id: String
    val title: String
    val type: Component.Type
}
