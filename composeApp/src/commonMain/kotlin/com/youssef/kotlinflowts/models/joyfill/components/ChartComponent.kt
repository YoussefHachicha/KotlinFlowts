package com.youssef.kotlinflowts.models.joyfill.components

import com.youssef.kotlinflowts.models.joyfill.components.chart.Axis
import com.youssef.kotlinflowts.models.joyfill.components.chart.Line
import com.youssef.kotlinflowts.models.joyfill.components.core.ListBasedComponent

interface ChartComponent : ListBasedComponent<Line> {
    val x: Axis
    val y: Axis
}