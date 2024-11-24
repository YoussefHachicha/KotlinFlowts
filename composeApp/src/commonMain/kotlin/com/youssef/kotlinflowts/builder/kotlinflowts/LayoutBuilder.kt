package com.youssef.kotlinflowts.builder.kotlinflowts

import com.youssef.kotlinflowts.builder.kotlinflowts.chart.LineBuilder
import com.youssef.kotlinflowts.builder.kotlinflowts.chart.LineBuilderImpl
import com.youssef.kotlinflowts.builder.kotlinflowts.column.ColumnBuilderImpl
import com.youssef.kotlinflowts.builder.kotlinflowts.row.RowBuilderImpl
import com.youssef.kotlinflowts.builder.kotlinflowts.table.TableColumnBuilder
import com.youssef.kotlinflowts.builder.kotlinflowts.table.TableColumnsBuilderImplTable
import com.youssef.kotlinflowts.models.kotlinflowts.ComponentPosition
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.MutableApp
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Axis
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Line
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Attachment
import com.youssef.kotlinflowts.models.kotlinflowts.utils.option
import kotlinx.coroutines.flow.StateFlow

interface LayoutBuilder {
    val identity: IdentityGenerator
    val app: MutableApp
    val components: StateFlow<List<Component>>
    val depth: Int
    val builderId: String

    private fun add(component: Component) {
        val position = componentPosition(
            id = identity.generate(),
            component = component.id,
            displayType = "original",
            depth = depth,
            builderId = builderId,
            format = null
        )
        add(component, position)
    }

    fun add(component: Component, position: ComponentPosition)

    fun addBuilder(wrapped: Pair<String, LayoutBuilder>){
        app.builders[wrapped.first] = wrapped.second
    }

    private fun <C : Component> buildComponent(id: String?, builder: (uid: String) -> C) {
        add(builder(id ?: identity.generate()))
    }

    fun add(type: Component.Type){
        when (type) {
            Component.Type.text -> text()
            Component.Type.textarea -> textarea()
            Component.Type.number -> number()
            Component.Type.dropdown -> dropdown(options = emptyList())
            Component.Type.multiSelect -> select(options = emptyList())
            Component.Type.date -> date()
            Component.Type.richText -> textarea()
            Component.Type.signature -> signature()
            Component.Type.table -> table()
            Component.Type.chart -> chart()
            Component.Type.image -> image()
            Component.Type.file -> file()
            Component.Type.block -> {}
            Component.Type.column -> column()
            Component.Type.row -> row()
            Component.Type.unknown -> {}
        }
    }

    fun delete(id: String)

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
            depth,
            builderId,
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
            depth,
            builderId,
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
            depth,
            builderId,
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
            depth,
            builderId,
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
            dateComponent(uid, title, identifier ?: "component-$uid", readonly, format, depth, builderId, value)
        val position = componentPosition(
            id = identity.generate(),
            component = component.id,
            displayType = "original",
            depth = depth,
            builderId = builderId,
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
        dropdownComponent(uid, title, identifier ?: "component-$uid", o, readonly, depth, builderId, selected)
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
        multiSelectComponent(uid, title, identifier ?: "component-$uid", o, depth, builderId, selected)
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
        imageComponent(uid, title, identifier ?: "component-$uid", readonly, depth, builderId, attachments)
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
            depth = depth,
            builderId = builderId,
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
            depth = depth,
            builderId = builderId,
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
            depth = depth,
            builderId = builderId,
            lines = lines
        )
    }

    fun column(
        title: String = "Column Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        app: MutableApp = myApp,
        components: (LayoutBuilder.() -> Unit)? = null
    ) = buildComponent(id) { uid ->
        val builderDepth = depth + 1
        val builder = ColumnBuilderImpl(identity, app, builderDepth, uid)
        addBuilder(uid to builder)
        components?.invoke(builder)
        columnComponent(
            id = uid,
            title = title,
            depth = depth,
            builderId = builderId,
            identifier = identifier ?: "component-$uid",
            components = builder.components.value
        )
    }

    fun row(
        title: String = "Row Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        app: MutableApp = myApp,
        components: (LayoutBuilder.() -> Unit)? = null
    ) = buildComponent(id) { uid ->
        val builderDepth = depth + 1
        val builder = RowBuilderImpl(identity, app, builderDepth, uid)
        addBuilder(uid to builder)

        components?.invoke(builder)
        rowComponent(
            id = uid,
            title = title,
            depth = depth,
            builderId = builderId,
            identifier = identifier ?: "component-$uid",
            components = builder.components.value
        )
    }
}
