package com.youssef.kotlinflowts.builder.joyfill.chart

import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Point
import com.youssef.kotlinflowts.models.joyfill.toPoint
import com.youssef.kotlinflowts.models.joyfill.utils.ID

@PublishedApi
internal class PointBuilderImpl(
    private val identity: IdentityGenerator
) : PointBuilder {
    val points = mutableListOf<Point>()
    override fun invoke(x: Number, y: Number, label: String?) {
        val point = mutableMapOf<String, Any?>(
            ID to identity.generate(),
            Point::label.name to (label ?: "Point ${points.size}"),
            Point::x.name to x,
            Point::y.name to y
        ).toPoint()
        points.add(point)
    }
}