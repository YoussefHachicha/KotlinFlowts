package com.youssef.kotlinflowts.editor.kotlinflowts.chart

import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Point

interface PointEditor {
    val point: Point

    fun label(value: String)
    fun label(): String

    fun x(value: Double)

    fun x(): Double

    fun y(value: Double)
    fun y(): Double
}