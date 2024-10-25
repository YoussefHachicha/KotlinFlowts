package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.editor.joyfill.chart.LineCollection
import com.youssef.kotlinflowts.models.joyfill.fields.ChartComponent

interface ChartComponentEditor : ComponentEditor {
    override val component: ChartComponent
    val lines: LineCollection
}