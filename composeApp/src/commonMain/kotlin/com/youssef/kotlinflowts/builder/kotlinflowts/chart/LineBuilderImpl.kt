package com.youssef.kotlinflowts.builder.kotlinflowts.chart

import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Line
import com.youssef.kotlinflowts.models.kotlinflowts.toLine
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID

internal class LineBuilderImpl(private val identity: IdentityGenerator) : LineBuilder {
    val lines = mutableListOf<Line>()
    override fun line(title: String, id: String?, description: String?, block: (point: PointBuilder) -> Unit) {
        val points = PointBuilderImpl(identity).also { block(it) }.points
        val line = mutableMapOf(
            ID to (id ?: identity.generate()),
            Line::title.name to title,
            Line::description.name to description,
            Line::points.name to points.map { it.toMap() }.toMutableList()
        ).toLine()
        lines.add(line)
    }
}
