package com.youssef.kotlinflowts.editor.joyfill.internal

import com.youssef.kotlinflowts.editor.joyfill.collections.FieldCollection
import com.youssef.kotlinflowts.editor.joyfill.editors.FieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.AnyFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.BlockFieldEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.ChartFieldEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.DateFieldEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.DropdownFieldEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.FileFieldEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.ImageFieldEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.MultiSelectFieldEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.NumberFieldEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.RichTextFieldEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.SignatureFieldEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.TableFieldEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.TextAreaFieldEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.TextFieldEditorImpl
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.MutableDocument
import com.youssef.kotlinflowts.models.joyfill.Page
import com.youssef.kotlinflowts.models.joyfill.fields.BlockField
import com.youssef.kotlinflowts.models.joyfill.fields.ChartField
import com.youssef.kotlinflowts.models.joyfill.fields.DateField
import com.youssef.kotlinflowts.models.joyfill.fields.DropdownField
import com.youssef.kotlinflowts.models.joyfill.fields.Field
import com.youssef.kotlinflowts.models.joyfill.fields.FileField
import com.youssef.kotlinflowts.models.joyfill.fields.ImageField
import com.youssef.kotlinflowts.models.joyfill.fields.MultiSelectField
import com.youssef.kotlinflowts.models.joyfill.fields.NumberField
import com.youssef.kotlinflowts.models.joyfill.fields.RichTextField
import com.youssef.kotlinflowts.models.joyfill.fields.SignatureField
import com.youssef.kotlinflowts.models.joyfill.fields.TableField
import com.youssef.kotlinflowts.models.joyfill.fields.TextAreaField
import com.youssef.kotlinflowts.models.joyfill.fields.TextField

internal class FieldCollectionImpl(
    private val document: MutableDocument,
    private val identity: IdentityGenerator,
    private val onChange: ((ChangeEvent) -> Unit)?
) : FieldCollection {
    override fun all() = document.fields.map { it.toEditor() }

    override fun from(page: String): List<FieldEditor> {
        val files = document.files
        val pages = files.flatMap { it.views }.flatMap { it.pages } + files.flatMap { it.pages }
        val p = pages.find {
            it.id == page || it.name == page
        } ?: return emptyList()
        return from(p)
    }

    override fun from(page: Page): List<FieldEditor> {
        val positions = page.positions
        val ids = positions.map { it.field }
        return document.fields.filter {
            it.id in ids
        }.sortedBy { df ->
            positions.first { it.field == df.id }.y
        }.map { it.toEditor() }
    }

    private fun <F : Field> look(key: String, type: Field.Type): F? = document.fields.find {
        (it.identifier == key || it.id == key || it.title == key) && it.type == type
    } as? F

    override fun find(key: String): FieldEditor? {
        val field = document.fields.find { it.identifier == key || it.id == key || it.title == key } ?: return null
        return field.toEditor()
    }

    private fun Field.toEditor(): FieldEditor = when (this) {
        is TextField -> TextFieldEditorImpl(document, this, onChange)
        is TextAreaField -> TextAreaFieldEditorImpl(document, this, onChange)
        is NumberField -> NumberFieldEditorImpl(document, this, onChange)
        is DropdownField -> DropdownFieldEditorImpl(document, this, onChange)
        is MultiSelectField -> MultiSelectFieldEditorImpl(document, this, onChange)
        is DateField -> DateFieldEditorImpl(document, this, onChange)
        is SignatureField -> SignatureFieldEditorImpl(document, this, onChange)
        is TableField -> TableFieldEditorImpl(document, this, identity, onChange)
        is ChartField -> ChartFieldEditorImpl(document, this, identity, onChange)
        is ImageField -> ImageFieldEditorImpl(document, this, identity, onChange)
        is FileField -> FileFieldEditorImpl(document, this, identity, onChange)
        is RichTextField -> RichTextFieldEditorImpl(document, this)
        is BlockField -> BlockFieldEditorImpl(document, this)
        else /*  is UnknownField */ -> AnyFieldEditor(document, this, onChange)
    }

    private fun <F : Field, E : FieldEditor> buildEditor(key: String, type: Field.Type, builder: (field: F) -> E?): E? {
        return builder(look(key, type) ?: return null)
    }

    override fun text(key: String) = buildEditor(key, Field.Type.text) { field: TextField ->
        TextFieldEditorImpl(document, field, onChange)
    }

    override fun textarea(key: String) = buildEditor(key, Field.Type.textarea) { field: TextAreaField ->
        TextAreaFieldEditorImpl(document, field, onChange)
    }

    override fun signature(key: String) = buildEditor(key, Field.Type.signature) { field: SignatureField ->
        SignatureFieldEditorImpl(document, field, onChange)
    }

    override fun date(key: String) = buildEditor(key, Field.Type.date) { field: DateField ->
        DateFieldEditorImpl(document, field, onChange)
    }

    override fun number(key: String) = buildEditor(key, Field.Type.number) { field: NumberField ->
        NumberFieldEditorImpl(document, field, onChange)
    }

    override fun dropdown(key: String) = buildEditor(key, Field.Type.dropdown) { field: DropdownField ->
        DropdownFieldEditorImpl(document, field, onChange)
    }

    override fun select(key: String) = buildEditor(key, Field.Type.multiSelect) { field: MultiSelectField ->
        MultiSelectFieldEditorImpl(document, field, onChange)
    }

    override fun image(key: String) = buildEditor(key, Field.Type.image) { field: ImageField ->
        ImageFieldEditorImpl(document, field, identity, onChange)
    }

    override fun file(key: String) = buildEditor(key, Field.Type.file) { field: FileField ->
        FileFieldEditorImpl(document, field, identity, onChange)
    }

    override fun table(key: String) = buildEditor(key, Field.Type.table) { field: TableField ->
        TableFieldEditorImpl(document, field, identity, onChange)
    }

    override fun chart(key: String) = buildEditor(key, Field.Type.chart) { field: ChartField ->
        ChartFieldEditorImpl(document, field, identity, onChange)
    }
}