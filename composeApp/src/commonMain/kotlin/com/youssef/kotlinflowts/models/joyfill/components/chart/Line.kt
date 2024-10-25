package com.youssef.kotlinflowts.models.joyfill.components.chart

import com.youssef.kotlinflowts.models.joyfill.Mappable

interface Line : Mappable {
    val id: String
    var title: String
    var description: String?
    val points: MutableList<Point>
}