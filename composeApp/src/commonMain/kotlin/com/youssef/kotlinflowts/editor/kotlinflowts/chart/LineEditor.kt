package com.youssef.kotlinflowts.editor.kotlinflowts.chart

import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Line
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Point

interface LineEditor {
    val line: Line

    fun title(value: String)
    fun title(): String

    fun points(): List<PointEditor>

    fun add(): PointEditor

    fun find(key: String): PointEditor?

    fun remove(key: String) : Point?
}