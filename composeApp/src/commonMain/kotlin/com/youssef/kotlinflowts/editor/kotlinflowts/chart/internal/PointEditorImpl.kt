package com.youssef.kotlinflowts.editor.kotlinflowts.chart.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.chart.PointEditor
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Point

@PublishedApi
internal class PointEditorImpl(
    override val point: Point
) : PointEditor {
    override fun label(value: String) {
        point.label = value
    }

    override fun label() = point.label

    override fun x(value: Double) {
        point.x = value
    }

    override fun x() = point.x

    override fun y(value: Double) {
        point.y = value
    }

    override fun y() = point.y
}