package com.youssef.kotlinflowts.models.joyfill.components.chart.internal

import com.youssef.kotlinflowts.models.joyfill.components.AbstractMappable
import com.youssef.kotlinflowts.models.joyfill.components.chart.Point
import com.youssef.kotlinflowts.models.joyfill.utils.ID

@PublishedApi
internal class PointImpl(wrapped: MutableMap<String, Any?>) : AbstractMappable(wrapped), Point {
    override var id: String
        get() = wrapped[ID] as String
        set(value) {
            wrapped[ID] = value
        }

    override var x: Double
        get() = wrapped[Point::x.name]?.toString()?.toDouble() as Double
        set(value) {
            wrapped[Point::x.name] = value
        }

    override var y: Double
        get() = wrapped[Point::y.name]?.toString()?.toDouble() as Double
        set(value) {
            wrapped[Point::y.name] = value
        }

    override var label: String
        get() = wrapped[Point::label.name] as String
        set(value) {
            wrapped[Point::label.name] = value
        }
}