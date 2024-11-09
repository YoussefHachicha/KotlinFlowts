package com.youssef.kotlinflowts.builder.kotlinflowts.chart

interface PointBuilder {
    operator fun invoke(x: Number, y: Number, label: String? = null)
}