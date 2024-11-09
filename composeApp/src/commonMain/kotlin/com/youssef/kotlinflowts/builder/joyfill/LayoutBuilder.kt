package com.youssef.kotlinflowts.builder.joyfill

import com.youssef.kotlinflowts.builder.joyfill.chart.LineBuilder
import com.youssef.kotlinflowts.builder.joyfill.chart.LineBuilderImpl
import com.youssef.kotlinflowts.builder.joyfill.column.ColumnBuilderImpl
import com.youssef.kotlinflowts.builder.joyfill.row.RowBuilderImpl
import com.youssef.kotlinflowts.builder.joyfill.table.TableColumnBuilder
import com.youssef.kotlinflowts.builder.joyfill.table.TableColumnsBuilderImplTable
import com.youssef.kotlinflowts.models.joyfill.ComponentPosition
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.components.chart.Axis
import com.youssef.kotlinflowts.models.joyfill.components.chart.Line
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.utils.Attachment
import com.youssef.kotlinflowts.models.joyfill.utils.option

interface LayoutBuilder {
    val identity: IdentityGenerator

    private fun add(component: Component) {
        val position = componentPosition(
            id = identity.generate(),
            component = component.id,
            displayType = "original",
            format = null
        )
        add(component, position)
    }

    fun add(component: Component, position: ComponentPosition)

    private fun <C : Component> buildComponent(id: String?, builder: (uid: String) -> C) {
        add(builder(id ?: identity.generate()))
    }

    fun text(
        title: String = "Text Field Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String? = null
    ) = buildComponent(id) { uid ->
        textComponent(
            uid,
            title,
            identifier ?: "component-$uid",
            readonly,
            value
        )
    }

    fun textarea(
        title: String = "Text Area Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String? = null
    ) = buildComponent(id) { uid ->
        textAreaComponent(
            uid,
            title,
            identifier ?: "component-$uid",
            readonly,
            value
        )
    }

    fun signature(
        title: String = "Signature",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String? = null
    ) = buildComponent(id) { uid ->
        signatureComponent(
            uid,
            title,
            identifier ?: "component-$uid",
            readonly,
            value
        )
    }

    fun number(
        title: String = "Number Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: Number? = null
    ) = buildComponent(id) { uid ->
        numberComponent(
            uid,
            title,
            identifier ?: "component-$uid",
            readonly,
            value
        )
    }

    fun date(
        title: String = "Date Component",
        id: String? = null,
        identifier: String? = null,
        format: String? = null,
        readonly: Boolean = false,
        value: Long? = null
    ) {
        val uid = id ?: identity.generate()
        val component =
            dateComponent(uid, title, identifier ?: "component-$uid", readonly, format, value)
        val position = componentPosition(
            id = identity.generate(),
            component = component.id,
            displayType = "original",
            format = format
        )
        add(component, position)
    }

    private fun String.toOption() = option(id = identity.generate(), value = this)

    fun dropdown(
        title: String = "Dropdown Component",
        options: List<String>,
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String? = null
    ) = buildComponent(id) { uid ->
        val o = options.map { it.toOption() }
        val selected = o.firstOrNull { it.value == value || it.id == value }
        dropdownComponent(uid, title, identifier ?: "component-$uid", o, readonly, selected)
    }

    fun select(
        title: String = "Select Component",
        options: List<String>,
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: List<String> = emptyList()
    ) = buildComponent(id) { uid ->
        val selected = mutableListOf<String>()
        val o = options.map {
            val out = it.toOption()
            if (value.contains(it)) {
                selected.add(out.id)
            }
            out
        }
        multiSelectComponent(uid, title, identifier ?: "component-$uid", o, selected)
    }

    fun select(
        title: String = "Select Component",
        options: List<String>,
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String
    ) = select(title, options, id, identifier, readonly, listOf(value))

    private fun String.toAttachment() = Attachment(id = identity.generate(), url = this)

    fun image(
        title: String = "Image Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: List<String> = emptyList()
    ) = buildComponent(id) { uid ->
        val attachments = value.map { it.toAttachment() }
        imageComponent(uid, title, identifier ?: "component-$uid", readonly, attachments)
    }

    fun image(
        title: String = "Image Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String
    ) = image(title, id, identifier, readonly, listOf(value))

    fun file(
        title: String = "File Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: List<String> = emptyList()
    ) = buildComponent(id) { uid ->
        fileComponent(
            uid,
            title,
            identifier ?: "component-$uid",
            value.map { it.toAttachment() })
    }

    fun table(
        title: String = "Table Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        columns: (TableColumnBuilder.() -> Unit)? = null
    ) = buildComponent(id) { uid ->
        val builder = TableColumnsBuilderImplTable(identity)
        columns?.invoke(builder)
        tableComponent(
            id = uid,
            title = title,
            identifier = identifier ?: "component-$uid",
            columns = builder.columns
        )
    }

    fun chart(
        title: String = "Chart Component",
        y: Axis = Axis("Y-Axis"),
        x: Axis = Axis("X-Axis"),
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        lines: (LineBuilder.() -> Unit)? = null
    ) = chart(
        title, y, x, id, identifier, readonly,
        lines = LineBuilderImpl(identity).also { lines?.invoke(it) }.lines
    )

    fun chart(
        title: String = "Chart Component",
        y: Axis = Axis("Y-Axis"),
        x: Axis = Axis("X-Axis"),
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        lines: List<Line>
    ) = buildComponent(id) { uid ->
        chartComponent(
            id = uid,
            title = title,
            identifier = identifier ?: "component-$uid",
            y = y,
            x = x,
            lines = lines
        )
    }

    fun column(
        title: String = "Column Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        components: (LayoutBuilder.() -> Unit)? = null
    ) = buildComponent(id) { uid ->
        val builder = ColumnBuilderImpl(identity)
        components?.invoke(builder)
        columnComponent(
            id = uid,
            title = title,
            identifier = identifier ?: "component-$uid",
            components = builder.columnComponents
        )
    }

    fun row(
        title: String = "Row Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        components: (LayoutBuilder.() -> Unit)? = null
    ) = buildComponent(id) { uid ->
        val builder = RowBuilderImpl(identity)
        components?.invoke(builder)
        rowComponent(
            id = uid,
            title = title,
            identifier = identifier ?: "component-$uid",
            components = builder.rowComponents
        )
    }
}
