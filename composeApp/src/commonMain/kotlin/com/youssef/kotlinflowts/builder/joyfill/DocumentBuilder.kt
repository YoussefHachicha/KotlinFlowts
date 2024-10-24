package com.youssef.kotlinflowts.builder.joyfill

import com.youssef.kotlinflowts.builder.joyfill.chart.LineBuilder
import com.youssef.kotlinflowts.builder.joyfill.table.ColumnBuilder
import com.youssef.kotlinflowts.models.joyfill.Page
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Axis
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Line

interface DocumentBuilder {
    fun name(value: String)

    fun page(name: String?): Page

    fun text(
        title: String = "Text Field",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String? = null
    )

    fun textarea(
        title: String = "Text Area",
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
        title: String = "Number Field",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: Number? = null
    )

    fun date(
        title: String = "Signature",
        id: String? = null,
        identifier: String? = null,
        format: String? = null,
        readonly: Boolean = false,
        value: Long? = null
    )

    fun dropdown(
        title: String = "Dropdown Field",
        options: List<String>,
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String? = null
    )

    fun select(
        title: String = "Select Field",
        options: List<String>,
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: List<String> = emptyList()
    )

    fun select(
        title: String = "Select Field",
        options: List<String>,
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String
    )

    fun image(
        title: String = "Image Field",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: List<String> = emptyList()
    )

    fun image(
        title: String = "Image Field",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String
    )

    fun file(
        title: String = "File Field",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: List<String> = emptyList()
    )

    fun table(
        title: String = "Table Field",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        columns: (ColumnBuilder.() -> Unit)? = null
    )

    fun chart(
        title: String = "Chart Field",
        y: Axis = Axis("Y-Axis"),
        x: Axis = Axis("X-Axis"),
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        lines: (LineBuilder.() -> Unit)? = null
    )

    fun chart(
        title: String = "Chart Field",
        y: Axis = Axis("Y-Axis"),
        x: Axis = Axis("X-Axis"),
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        lines: List<Line>
    )
}