package com.youssef.kotlinflowts.models.joyfill.components.chart.internal

import com.youssef.kotlinflowts.models.joyfill.components.AbstractMappable
import com.youssef.kotlinflowts.models.joyfill.components.chart.Line
import com.youssef.kotlinflowts.models.joyfill.components.chart.Point
import com.youssef.kotlinflowts.models.joyfill.toPoint
import com.youssef.kotlinflowts.models.joyfill.utils.ID
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList

@PublishedApi
internal class LineImpl(wrapped: MutableMap<String, Any?>) : AbstractMappable(wrapped), Line {
    override val id get() = wrapped[ID] as String

    override var title: String
        get() = wrapped[Line::title.name] as String
        set(value) {
            wrapped[Line::title.name] = value
        }

    override var description: String?
        get() = wrapped[Line::description.name] as? String
        set(value) {
            wrapped[Line::description.name] = value
        }

    override val points: MutableList<Point> = run {
        val key = Line::points.name
        val value = wrapped[key]
        if (value == null) {
            wrapped[key] = mutableListOf<MutableMap<String, Any>>()
        }
        JsonList(wrapped[key]) { it.toPoint() }
    }
}