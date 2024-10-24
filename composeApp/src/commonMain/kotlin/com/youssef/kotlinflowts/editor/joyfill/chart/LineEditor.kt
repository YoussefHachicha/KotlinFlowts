package com.youssef.kotlinflowts.editor.joyfill.chart

import com.youssef.kotlinflowts.models.joyfill.fields.chart.Line
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Point

interface LineEditor {
    val line: Line

    fun title(value: String)
    fun title(): String

    fun points(): List<PointEditor>

    fun add(): PointEditor

    fun find(key: String): PointEditor?

    fun remove(key: String) : Point?
}