package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.editor.kotlinflowts.chart.LineCollection
import com.youssef.kotlinflowts.models.kotlinflowts.components.ChartComponent

interface ChartComponentEditor : ComponentEditor {
    override val comp: ChartComponent
    val lines: LineCollection
}