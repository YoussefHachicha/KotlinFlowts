package com.youssef.kotlinflowts.builder.joyfill.internal

import com.youssef.kotlinflowts.builder.joyfill.ChartField
import com.youssef.kotlinflowts.builder.joyfill.DateField
import com.youssef.kotlinflowts.builder.joyfill.DocumentBuilder
import com.youssef.kotlinflowts.builder.joyfill.DropdownField
import com.youssef.kotlinflowts.builder.joyfill.FieldPosition
import com.youssef.kotlinflowts.builder.joyfill.File
import com.youssef.kotlinflowts.builder.joyfill.FileField
import com.youssef.kotlinflowts.builder.joyfill.ImageField
import com.youssef.kotlinflowts.builder.joyfill.MultiSelectField
import com.youssef.kotlinflowts.builder.joyfill.NumberField
import com.youssef.kotlinflowts.builder.joyfill.Page
import com.youssef.kotlinflowts.builder.joyfill.SignatureField
import com.youssef.kotlinflowts.builder.joyfill.TableField
import com.youssef.kotlinflowts.builder.joyfill.TextArea
import com.youssef.kotlinflowts.builder.joyfill.TextField
import com.youssef.kotlinflowts.builder.joyfill.chart.LineBuilder
import com.youssef.kotlinflowts.builder.joyfill.chart.LineBuilderImpl
import com.youssef.kotlinflowts.builder.joyfill.table.ColumnBuilder
import com.youssef.kotlinflowts.builder.joyfill.table.TableColumnsBuilderImpl
import com.youssef.kotlinflowts.models.joyfill.FieldPosition
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.MutableDocument
import com.youssef.kotlinflowts.models.joyfill.MutablePage
import com.youssef.kotlinflowts.models.joyfill.fields.Field
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Axis
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Line
import com.youssef.kotlinflowts.models.joyfill.utils.Attachment
import com.youssef.kotlinflowts.models.joyfill.utils.Option

class DocumentBuilderImpl(
    internal val document: MutableDocument,
    private val identity: IdentityGenerator
) : DocumentBuilder {

    private val file by lazy {
        document.files.getOrNull(0) ?: File(
            id = identity.generate(),
            name = document.name,
            pages = mutableListOf(),
            pageOrder = mutableListOf()
        ).also { document.files.add(it) }
    }

    private var cursor: MutablePage? = null

    override fun name(value: String) {
        document.name = value
        file.name = value
    }

    private fun cursor(): MutablePage {
        return cursor ?: page("New Page")
    }

    override fun page(name: String?): MutablePage {
        val p = Page(
            id = identity.generate(),
            name = name ?: "Page ${file.pages.size + 1}",
            positions = mutableListOf()
        )
        cursor = p
        file.pages.add(p)
        file.pageOrder.add(p.id)
        return p
    }

    private fun add(field: Field) {
        val position = FieldPosition(
            id = identity.generate(),
            field = field.id,
            displayType = "original",
            format = null
        )
        add(field, position)
    }

    private fun add(field: Field, position: FieldPosition) {
        cursor().positions.add(position)
        document.fields.add(field)
    }

    private fun <F : Field> buildField(id: String?, builder: (uid: String) -> F) {
        add(builder(id ?: identity.generate()))
    }

    override fun text(
        title: String,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: String?
    ) = buildField(id) { uid -> TextField(uid, title, identifier ?: "field-$uid", readonly, value) }

    override fun textarea(
        title: String,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: String?
    ) = buildField(id) { uid -> TextArea(uid, title, identifier ?: "field-$uid", readonly, value) }

    override fun signature(
        title: String,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: String?
    ) = buildField(id) { uid -> SignatureField(uid, title, identifier ?: "field-$uid", readonly, value) }

    override fun number(
        title: String,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: Number?
    ) = buildField(id) { uid -> NumberField(uid, title, identifier ?: "field-$uid", readonly, value) }

    override fun date(
        title: String,
        id: String?,
        identifier: String?,
        format: String?,
        readonly: Boolean,
        value: Long?
    ) {
        val uid = id ?: identity.generate()
        val field = DateField(uid, title, identifier ?: "field-$uid", readonly, format, value)
        val position = FieldPosition(
            id = identity.generate(),
            field = field.id,
            displayType = "original",
            format = format
        )
        add(field, position)
    }

    private fun String.toOption() = Option(id = identity.generate(), value = this)

    override fun dropdown(
        title: String,
        options: List<String>,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: String?
    ) = buildField(id) { uid ->
        val o = options.map { it.toOption() }
        val selected = o.firstOrNull { it.value == value || it.id == value }
        DropdownField(uid, title, identifier ?: "field-$uid", o, readonly, selected)
    }

    override fun select(
        title: String,
        options: List<String>,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        value: List<String>
    ) = buildField(id) { uid ->
        val selected = mutableListOf<String>()
        val o = options.map {
            val out = it.toOption()
            if (value.contains(it)) {
                selected.add(out.id)
            }
            out
        }
        MultiSelectField(uid, title, identifier ?: "field-$uid", o, selected)
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
    ) = buildField(id) { uid ->
        val attachments = value.map { it.toAttachment() }
        ImageField(uid, title, identifier ?: "field-$uid", readonly, attachments)
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
    ) = buildField(id) { uid -> FileField(uid, title, identifier ?: "field-$uid", value.map { it.toAttachment() }) }

    override fun table(
        title: String,
        id: String?,
        identifier: String?,
        readonly: Boolean,
        columns: (ColumnBuilder.() -> Unit)?
    ) = buildField(id) { uid ->
        val builder = TableColumnsBuilderImpl(identity)
        columns?.invoke(builder)
        TableField(
            id = uid,
            title = title,
            identifier = identifier ?: "field-$uid",
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
    ) = buildField(id) { uid ->
        ChartField(
            id = uid,
            title = title,
            identifier = identifier ?: "field-$uid",
            y = y,
            x = x,
            lines = lines
        )
    }
}