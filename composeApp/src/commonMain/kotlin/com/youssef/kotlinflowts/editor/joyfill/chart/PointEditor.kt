package com.youssef.kotlinflowts.editor.joyfill.chart

import com.youssef.kotlinflowts.models.joyfill.fields.chart.Point

interface PointEditor {
    val point: Point

    fun label(value: String)
    fun label(): String

    fun x(value: Double)

    fun x(): Double

    fun y(value: Double)
    fun y(): Double
}