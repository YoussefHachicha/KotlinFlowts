package com.youssef.kotlinflowts.models.joyfill.fields

import com.youssef.kotlinflowts.models.joyfill.fields.chart.Axis
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Line

interface ChartField : ListBasedField<Line> {
    val x: Axis
    val y: Axis
}