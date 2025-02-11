package com.youssef.kotlinflowts.editor.kotlinflowts.table.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.table.DropdownCellEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.table.ImageCellEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.table.RowEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.table.TextCellEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.components.TableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Column
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.DropdownColumn
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.ImageColumn
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Row
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.TextColumn
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

internal class RowEditorImpl(
    val app: App,
    val field: TableComponent,
    val identity: IdentityGenerator,
    override val row: Row,
    val onChange: ((ChangeEvent) -> Unit)?
) : RowEditor {

    private fun <C : Column> find(key: String?, type: Component.Type) = field.columns.find {
        (it.id == key || it.title == key) && it.type == type
    } as? C

    private fun <C : Column> take(index: Int, type: Component.Type) : C? {
        val column = field.columns.getOrNull(index) ?: return null
        if(column.type != type) return null
        return column as? C
    }

    override fun text(key: String): TextCellEditor? {
        val column = find<TextColumn>(key, Component.Type.textField) ?: return null
        return TextCellEditorImpl(app, field, column, row, onChange)
    }

    override fun text(index: Int): TextCellEditor? {
        val column = take<TextColumn>(index, Component.Type.textField) ?: return null
        return TextCellEditorImpl(app, field, column, row, onChange)
    }

    override fun image(key: String): ImageCellEditor? {
        val column = find<ImageColumn>(key, Component.Type.image) ?: return null
        return ImageCellEditorImpl(app, field, identity, column, row, onChange)
    }

    override fun image(index: Int): ImageCellEditor? {
        val column = take<ImageColumn>(index, Component.Type.image) ?: return null
        return ImageCellEditorImpl(app, field, identity, column, row, onChange)
    }

    override fun dropdown(key: String): DropdownCellEditor? {
        val column = find<DropdownColumn>(key, Component.Type.dropdown) ?: return null
        return DropdownCellEditorImpl(app, field, identity, column, row, onChange)
    }

    override fun dropdown(index: Int): DropdownCellEditor? {
        val column = take<DropdownColumn>(index, Component.Type.dropdown) ?: return null
        return DropdownCellEditorImpl(app, field, identity, column, row, onChange)
    }

    private fun Column.toEditor() = when(this) {
        is TextColumn -> TextCellEditorImpl(app, field, this, row, onChange)
        is ImageColumn -> ImageCellEditorImpl(app, field, identity, this, row, onChange)
        is DropdownColumn -> DropdownCellEditorImpl(app, field,  identity,this, row, onChange)
        else -> UnknownCellEditor
    }

    override fun col(key: String) = field.columns.find {
        it.id == key || it.title == key
    }?.toEditor()

    override fun col(index: Int) = field.columns.getOrNull(index)?.toEditor()
}