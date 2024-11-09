package com.youssef.kotlinflowts.models.kotlinflowts.components.chart

import com.youssef.kotlinflowts.models.kotlinflowts.Mappable

interface Point : Mappable {
    var id: String
    var x: Double
    var y: Double
    var label: String
}