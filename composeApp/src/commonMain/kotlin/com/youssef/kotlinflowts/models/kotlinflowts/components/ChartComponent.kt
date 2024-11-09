package com.youssef.kotlinflowts.models.kotlinflowts.components

import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Axis
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Line
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ListBasedComponent

interface ChartComponent : ListBasedComponent<Line> {
    val x: Axis
    val y: Axis
}