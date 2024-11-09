package com.youssef.kotlinflowts.builder.kotlinflowts.chart

interface LineBuilder {
    fun line(
        title: String,
        id: String? = null,
        description: String? = null,
        block: (point: PointBuilder) -> Unit
    )
}


