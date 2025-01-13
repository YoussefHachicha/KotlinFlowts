package com.youssef.kotlinflowts.builder.kotlinflowts

import com.youssef.kotlinflowts.builder.kotlinflowts.chart.LineBuilder
import com.youssef.kotlinflowts.builder.kotlinflowts.chart.LineBuilderImpl
import com.youssef.kotlinflowts.builder.kotlinflowts.column.ColumnBuilderImpl
import com.youssef.kotlinflowts.builder.kotlinflowts.row.RowBuilderImpl
import com.youssef.kotlinflowts.builder.kotlinflowts.table.TableColumnBuilder
import com.youssef.kotlinflowts.builder.kotlinflowts.table.TableColumnsBuilderImplTable
import com.youssef.kotlinflowts.editor.kotlinflowts.column.internal.ColumnComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.AnyComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.BlockComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.ChartComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.DateFieldFieldComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.DropdownComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.FileComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.ImageComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.MultiSelectComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.NumberFieldComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.RichTextComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.SignatureComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.TableComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.TextComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.TextFieldAreaComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.TextFieldComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.row.internal.RowComponentEditorImpl
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.ComponentPosition
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.MutableApp
import com.youssef.kotlinflowts.models.kotlinflowts.components.BlockComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.ChartComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.ColumnComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.DateFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.DropdownComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.FileComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.ImageComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.MultiSelectComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.NumberFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.RichTextComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.RowComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.SignatureComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextFieldAreaComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Axis
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Line
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Attachment
import com.youssef.kotlinflowts.models.kotlinflowts.utils.option
import kotlinx.coroutines.flow.StateFlow

interface LayoutBuilder {
    val identity: IdentityGenerator
    val app: MutableApp
    val components: List<ComponentEditor>
    val depth: Int
    val builderId: String
    val onChange: ((ChangeEvent) -> Unit)?

    fun Component.toEditor(): ComponentEditor = when (this) {
        is TextComponent                -> TextComponentEditorImpl(app, this, onChange)
        is TextFieldComponent           -> TextFieldComponentEditorImpl(app, this, onChange)
        is TextFieldAreaComponent       -> TextFieldAreaComponentEditorImpl(app, this, onChange)
        is NumberFieldComponent         -> NumberFieldComponentEditorImpl(app, this, onChange)
        is DropdownComponent            -> DropdownComponentEditorImpl(app, this, identity, onChange)
        is MultiSelectComponent         -> MultiSelectComponentEditorImpl(app, this, identity, onChange)
        is DateFieldComponent           -> DateFieldFieldComponentEditorImpl(app, this, onChange)
        is SignatureComponent           -> SignatureComponentEditorImpl(app, this, onChange)
        is TableComponent               -> TableComponentEditorImpl(app, this, identity, onChange)
        is ChartComponent               -> ChartComponentEditorImpl(app, this, identity, onChange)
        is ImageComponent               -> ImageComponentEditorImpl(app, this, identity, onChange)
        is FileComponent                -> FileComponentEditorImpl(app, this, identity, onChange)
        is RichTextComponent            -> RichTextComponentEditorImpl(app, this)
        is BlockComponent               -> BlockComponentEditorImpl(app, this)
        is ColumnComponent              -> ColumnComponentEditorImpl(app, this, identity, onChange)
        is RowComponent                 -> RowComponentEditorImpl(app, this, identity, onChange)
        else /*  is UnknownComponent */ -> AnyComponentEditor(app, this, onChange)
    }


    private fun add(component: Component) {
        val position = componentPosition(
            id = identity.generate(),
            component = component.id,
            displayType = "original",
            depth = depth,
            builderId = builderId,
            format = null
        )
        add(component.toEditor(), position)
    }



    fun add(component: ComponentEditor, position: ComponentPosition)

    fun addBuilder(wrapped: Pair<String, LayoutBuilder>){
        app.builders[wrapped.first] = wrapped.second
    }

    private fun <C : Component> buildComponent(id: String?, builder: (uid: String) -> C) {
        add(builder(id ?: identity.generate()))
    }

    fun add(type: Component.Type){
        when (type) {
            Component.Type.text          -> text()
            Component.Type.textField     -> textField()
            Component.Type.textFieldArea -> textFieldArea()
            Component.Type.numberField   -> numberField()
            Component.Type.dropdown      -> dropdown(options = emptyList())
            Component.Type.multiSelect -> select(options = emptyList())
            Component.Type.dateField   -> dateField()
            Component.Type.richText    -> textFieldArea()
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
        title: String = "Text Component",
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

    fun textField(
        title: String = "TextField Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String? = null
    ) = buildComponent(id) { uid ->
        textFieldComponent(
            uid,
            title,
            identifier ?: "component-$uid",
            readonly,
            depth,
            builderId,
            value
        )
    }

    fun textFieldArea(
        title: String = "TextField Area Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String? = null
    ) = buildComponent(id) { uid ->
        textFieldAreaComponent(
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

    fun numberField(
        title: String = "NumberField Component",
        id: String? = null,
        identifier: String? = null,
        readonly: Boolean = false,
        value: Number? = null
    ) = buildComponent(id) { uid ->
        numberFieldComponent(
            uid,
            title,
            identifier ?: "component-$uid",
            readonly,
            depth,
            builderId,
            value
        )
    }

    fun dateField(
        title: String = "DateField Component",
        id: String? = null,
        identifier: String? = null,
        format: String? = null,
        readonly: Boolean = false,
        value: Long? = null
    ) {
        val uid = id ?: identity.generate()
        val component =
            dateFieldComponent(uid, title, identifier ?: "component-$uid", readonly, format, depth, builderId, value)
        val position = componentPosition(
            id = identity.generate(),
            component = component.id,
            displayType = "original",
            depth = depth,
            builderId = builderId,
            format = format
        )
        add(component.toEditor(), position)
    }

    private fun String.toOption() = option(id = identity.generate(), value = this)

    fun dropdown(
        title: String = "Dropdown Component",
        options: List<String>,
        id: String? = null,
        multiple: Boolean = false,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String? = null
    ) = buildComponent(id) { uid ->
        val o = options.map { it.toOption() }
        val selected = o.firstOrNull { it.value == value || it.id == value }
        dropdownComponent(uid, title, identifier ?: "component-$uid", multiple, o, readonly, depth, builderId, selected)
    }

    fun select(
        title: String = "Select Component",
        options: List<String>,
        id: String? = null,
        multiple: Boolean = true,
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
        multiSelectComponent(uid, title, identifier ?: "component-$uid", multiple, o, depth, builderId, selected)
    }

    fun select(
        title: String = "Select Component",
        options: List<String>,
        id: String? = null,
        multiple: Boolean = true,
        identifier: String? = null,
        readonly: Boolean = false,
        value: String
    ) = select(title, options, id, multiple, identifier, readonly, listOf(value))

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
        val builder = ColumnBuilderImpl(identity, app, builderDepth, uid, onChange)
        addBuilder(uid to builder)
        components?.invoke(builder)
        columnComponent(
            id = uid,
            title = title,
            depth = depth,
            builderId = builderId,
            identifier = identifier ?: "component-$uid",
            components = builder.components.map { it.comp }
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
        val builder = RowBuilderImpl(identity, app, builderDepth, uid, onChange)
        addBuilder(uid to builder)

        components?.invoke(builder)
        rowComponent(
            id = uid,
            title = title,
            depth = depth,
            builderId = builderId,
            identifier = identifier ?: "component-$uid",
            components = builder.components.map { it.comp }
        )
    }
}
