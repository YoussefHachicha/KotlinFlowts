package com.youssef.kotlinflowts.models.kotlinflowts.components.chart

import com.youssef.kotlinflowts.models.kotlinflowts.Mappable

interface Line : Mappable {
    val id: String
    var title: String
    var description: String?
    val points: MutableList<Point>
}