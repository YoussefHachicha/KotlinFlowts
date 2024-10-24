package com.youssef.kotlinflowts.editor.joyfill.table.internal

import com.youssef.kotlinflowts.editor.joyfill.table.DropdownCellEditor
import com.youssef.kotlinflowts.editor.joyfill.table.ImageCellEditor
import com.youssef.kotlinflowts.editor.joyfill.table.RowEditor
import com.youssef.kotlinflowts.editor.joyfill.table.TextCellEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.fields.Field
import com.youssef.kotlinflowts.models.joyfill.fields.TableField
import com.youssef.kotlinflowts.models.joyfill.fields.table.Column
import com.youssef.kotlinflowts.models.joyfill.fields.table.DropdownColumn
import com.youssef.kotlinflowts.models.joyfill.fields.table.ImageColumn
import com.youssef.kotlinflowts.models.joyfill.fields.table.Row
import com.youssef.kotlinflowts.models.joyfill.fields.table.TextColumn
import com.youssef.kotlinflowts.models.joyfill.utils.Document

internal class RowEditorImpl(
    val document: Document,
    val field: TableField,
    val identity: IdentityGenerator,
    override val row: Row,
    val onChange: ((ChangeEvent) -> Unit)?
) : RowEditor {

    private fun <C : Column> find(key: String?, type: Field.Type) = field.columns.find {
        (it.id == key || it.title == key) && it.type == type
    } as? C

    private fun <C : Column> take(index: Int, type: Field.Type) : C? {
        val column = field.columns.getOrNull(index) ?: return null
        if(column.type != type) return null
        return column as? C
    }

    override fun text(key: String): TextCellEditor? {
        val column = find<TextColumn>(key, Field.Type.text) ?: return null
        return TextCellEditorImpl(document, field, column, row, onChange)
    }

    override fun text(index: Int): TextCellEditor? {
        val column = take<TextColumn>(index, Field.Type.text) ?: return null
        return TextCellEditorImpl(document, field, column, row, onChange)
    }

    override fun image(key: String): ImageCellEditor? {
        val column = find<ImageColumn>(key, Field.Type.image) ?: return null
        return ImageCellEditorImpl(document, field, identity, column, row, onChange)
    }

    override fun image(index: Int): ImageCellEditor? {
        val column = take<ImageColumn>(index, Field.Type.image) ?: return null
        return ImageCellEditorImpl(document, field, identity, column, row, onChange)
    }

    override fun dropdown(key: String): DropdownCellEditor? {
        val column = find<DropdownColumn>(key, Field.Type.dropdown) ?: return null
        return DropdownCellEditorImpl(document, field, column, row, onChange)
    }

    override fun dropdown(index: Int): DropdownCellEditor? {
        val column = take<DropdownColumn>(index, Field.Type.dropdown) ?: return null
        return DropdownCellEditorImpl(document, field, column, row, onChange)
    }

    private fun Column.toEditor() = when(this) {
        is TextColumn -> TextCellEditorImpl(document, field, this, row, onChange)
        is ImageColumn -> ImageCellEditorImpl(document, field, identity, this, row, onChange)
        is DropdownColumn -> DropdownCellEditorImpl(document, field, this, row, onChange)
        else -> UnknownCellEditor
    }

    override fun col(key: String) = field.columns.find {
        it.id == key || it.title == key
    }?.toEditor()

    override fun col(index: Int) = field.columns.getOrNull(index)?.toEditor()
}