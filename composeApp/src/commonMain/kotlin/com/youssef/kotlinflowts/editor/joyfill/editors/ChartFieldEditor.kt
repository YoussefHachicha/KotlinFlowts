package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.editor.joyfill.chart.LineCollection
import com.youssef.kotlinflowts.models.joyfill.fields.ChartField

interface ChartFieldEditor : FieldEditor {
    override val field: ChartField
    val lines: LineCollection
}