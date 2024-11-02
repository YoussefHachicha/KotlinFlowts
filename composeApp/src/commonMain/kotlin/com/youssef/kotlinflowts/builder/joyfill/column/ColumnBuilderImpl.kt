package com.youssef.kotlinflowts.builder.joyfill.column

import com.youssef.kotlinflowts.builder.joyfill.chart.LineBuilder
import com.youssef.kotlinflowts.builder.joyfill.chart.LineBuilderImpl
import com.youssef.kotlinflowts.builder.joyfill.chartComponent
import com.youssef.kotlinflowts.builder.joyfill.columnComponent
import com.youssef.kotlinflowts.builder.joyfill.componentPosition
import com.youssef.kotlinflowts.builder.joyfill.dateComponent
import com.youssef.kotlinflowts.builder.joyfill.dropdownComponent
import com.youssef.kotlinflowts.builder.joyfill.fileComponent
import com.youssef.kotlinflowts.builder.joyfill.imageComponent
import com.youssef.kotlinflowts.builder.joyfill.multiSelectComponent
import com.youssef.kotlinflowts.builder.joyfill.numberComponent
import com.youssef.kotlinflowts.builder.joyfill.signatureComponent
import com.youssef.kotlinflowts.builder.joyfill.table.TableColumnsBuilderImplTable
import com.youssef.kotlinflowts.builder.joyfill.tableComponent
import com.youssef.kotlinflowts.builder.joyfill.textAreaComponent
import com.youssef.kotlinflowts.builder.joyfill.textComponent
import com.youssef.kotlinflowts.models.joyfill.ComponentPosition
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.components.chart.Axis
import com.youssef.kotlinflowts.models.joyfill.components.chart.Line
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.utils.Attachment
import com.youssef.kotlinflowts.models.joyfill.utils.option

class ColumnBuilderImpl(private val identity: IdentityGenerator): ColumnBuilder {
    val columnComponents = mutableListOf<Component>()

    private fun add(component: Component) {
        val position = componentPosition(
            id = identity.generate(),
            component = component.id,
            displayType = "original",
            format = null
        )
        add(component, position)
    }

    private fun add(component: Component, position: ComponentPosition) {
        columnComponents.add(component)
    }

    private fun <C : Component> buildComponent(id: String?, builder: (uid: String) -> C) {
        add(builder(id ?: identity.generate()))
    }


    override fun text(
        title: String,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: String?
    ) = buildComponent(id) { uid -> textComponent(uid, title, identifier ?: "component-$uid", readonly, value) }

    override fun textarea(
        title: String,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: String?
    ) = buildComponent(id) { uid -> textAreaComponent(uid, title, identifier ?: "component-$uid", readonly, value) }

    override fun signature(
        title: String,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: String?
    ) = buildComponent(id) { uid -> signatureComponent(uid, title, identifier ?: "component-$uid", readonly, value) }

    override fun number(
        title: String,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: Number?
    ) = buildComponent(id) { uid -> numberComponent(uid, title, identifier ?: "component-$uid", readonly, value) }

    override fun date(
        title: String,
        id: String?,
        identifier: String?,
        format: String?,
        readonly: Boolean,
        value: Long?
    ) {
        val uid = id ?: identity.generate()
        val component = dateComponent(uid, title, identifier ?: "component-$uid", readonly, format, value)
        val position = componentPosition(
            id = identity.generate(),
            component = component.id,
            displayType = "original",
            format = format
        )
        add(component, position)
    }

    private fun String.toOption() = option(id = identity.generate(), value = this)

    override fun dropdown(
        title: String,
        options: List<String>,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: String?
    ) = buildComponent(id) { uid ->
        val o = options.map { it.toOption() }
        val selected = o.firstOrNull { it.value == value || it.id == value }
        dropdownComponent(uid, title, identifier ?: "component-$uid", o, readonly, selected)
    }

    override fun select(
        title: String,
        options: List<String>,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: List<String>
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

    override fun select(
        title: String,
        options: List<String>,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: String
    ) = select(title, options, id, identifier, readonly, listOf(value))

    private fun String.toAttachment() = Attachment(id = identity.generate(), url = this)

    override fun image(
        title: String,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: List<String>
    ) = buildComponent(id) { uid ->
        val attachments = value.map { it.toAttachment() }
        imageComponent(uid, title, identifier ?: "component-$uid", readonly, attachments)
    }

    override fun image(
        title: String,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: String
    ) = image(title, id, identifier, readonly, listOf(value))

    override fun file(
        title: String,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: List<String>
    ) = buildComponent(id) { uid -> fileComponent(uid, title, identifier ?: "component-$uid", value.map { it.toAttachment() }) }

    override fun table(
        title: String,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        columns: (com.youssef.kotlinflowts.builder.joyfill.table.TableColumnBuilder.() -> Unit)?
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

    override fun chart(
        title: String,
        y: Axis,
        x: Axis,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        lines: (LineBuilder.() -> Unit)?
    ) = chart(
        title, y, x, id, identifier, readonly,
        lines = LineBuilderImpl(identity).also { lines?.invoke(it) }.lines
    )

    override fun chart(
        title: String,
        y: Axis,
        x: Axis,
        id: String?,
        identifier: String?,
        readonly: Boolean,
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

    override fun column(
        title: String,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        components: (ColumnBuilder.() -> Unit)?
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
}