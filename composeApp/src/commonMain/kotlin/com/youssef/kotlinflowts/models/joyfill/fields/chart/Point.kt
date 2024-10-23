package com.youssef.kotlinflowts.models.joyfill.fields.chart

import com.youssef.kotlinflowts.models.joyfill.Mappable

interface Point : Mappable {
    var id: String
    var x: Double
    var y: Double
    var label: String
}