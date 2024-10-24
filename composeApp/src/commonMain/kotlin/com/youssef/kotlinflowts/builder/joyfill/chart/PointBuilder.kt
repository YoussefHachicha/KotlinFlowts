package com.youssef.kotlinflowts.builder.joyfill.chart

interface PointBuilder {
    operator fun invoke(x: Number, y: Number, label: String? = null)
}