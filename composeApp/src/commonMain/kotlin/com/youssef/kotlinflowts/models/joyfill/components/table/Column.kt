package com.youssef.kotlinflowts.models.joyfill.components.table

import com.youssef.kotlinflowts.models.joyfill.Mappable
import com.youssef.kotlinflowts.models.joyfill.components.core.Component

interface Column : Mappable {
    val id: String
    val title: String
    val type: Component.Type
}
