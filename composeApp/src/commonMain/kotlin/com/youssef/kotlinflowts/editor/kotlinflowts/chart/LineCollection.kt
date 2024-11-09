package com.youssef.kotlinflowts.editor.kotlinflowts.chart

import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Line

interface LineCollection {
    fun all(): List<LineEditor>

    fun find(key: String): LineEditor?

    fun add(
        title: String,
        id: String? = null,
        description: String? = null
    ): LineEditor

    fun remove(key: String): Line?
}