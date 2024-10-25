package com.youssef.kotlinflowts.builder.joyfill

import com.youssef.kotlinflowts.builder.joyfill.chart.LineBuilder
import com.youssef.kotlinflowts.builder.joyfill.table.ColumnBuilder
import com.youssef.kotlinflowts.models.joyfill.Screen
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Axis
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Line

interface AppBuilder {
    fun name(value: String)

    fun screen(name: String?): Screen

    fun text(
        title: String = "Text Field Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String? = null
    )

    fun textarea(
        title: String = "Text Area Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String? = null
    )

    fun signature(
        title: String = "Signature",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String? = null
    )

    fun number(
        title: String = "Number Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: Number? = null
    )

    fun date(
        title: String = "Signature Component",
        id: String? = null,
        identifier: String? = null,
        format: String? = null,
        readonly: Boolean = false,
        value: Long? = null
    )

    fun dropdown(
        title: String = "Dropdown Component",
        options: List<String>,
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String? = null
    )

    fun select(
        title: String = "Select Component",
        options: List<String>,
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: List<String> = emptyList()
    )

    fun select(
        title: String = "Select Component",
        options: List<String>,
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String
    )

    fun image(
        title: String = "Image Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: List<String> = emptyList()
    )

    fun image(
        title: String = "Image Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String
    )

    fun file(
        title: String = "File Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: List<String> = emptyList()
    )

    fun table(
        title: String = "Table Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        columns: (ColumnBuilder.() -> Unit)? = null
    )

    fun chart(
        title: String = "Chart Component",
        y: Axis = Axis("Y-Axis"),
        x: Axis = Axis("X-Axis"),
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        lines: (LineBuilder.() -> Unit)? = null
    )

    fun chart(
        title: String = "Chart Component",
        y: Axis = Axis("Y-Axis"),
        x: Axis = Axis("X-Axis"),
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        lines: List<Line>
    )

    fun column(
        title: String = "Column Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        components: (AppBuilder.() -> Unit)? = null
    )
}