package com.youssef.kotlinflowts.editor.joyfill.chart.internal

import com.youssef.kotlinflowts.editor.joyfill.chart.LineEditor
import com.youssef.kotlinflowts.editor.joyfill.chart.PointEditor
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Line
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Point
import com.youssef.kotlinflowts.models.joyfill.toPoint
import com.youssef.kotlinflowts.models.joyfill.utils.ID

@PublishedApi
internal class LineEditorImpl(
    private val identity: IdentityGenerator,
    override val line: Line
) : LineEditor {
    override fun title(value: String) {
        line.title = value
    }

    override fun title() = line.title

    override fun points(): List<PointEditor> = line.points.map {
        PointEditorImpl(it)
    }

    private fun look(key: String): Point? = line.points.find { it.id == key || it.label == key }

    override fun find(key: String): PointEditor? {
        val point = look(key) ?: return null
        return PointEditorImpl(point)
    }

    override fun add(): PointEditor {
        val point = mutableMapOf<String, Any?>(
            ID to identity.generate(),
            Point::label.name to "Point ${line.points.size + 1}",
            Point::x.name to 0.0,
            Point::y.name to 0.0
        ).toPoint()
        line.points.add(point)
        return PointEditorImpl(point)
    }

    override fun remove(key: String): Point? {
        val point = look(key) ?: return null
        line.points.remove(point)
        return point
    }
}