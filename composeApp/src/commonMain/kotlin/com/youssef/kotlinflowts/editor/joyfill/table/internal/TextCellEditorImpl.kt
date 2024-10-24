package com.youssef.kotlinflowts.editor.joyfill.table.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.internal.EventTrigger
import com.youssef.kotlinflowts.editor.joyfill.table.TextCellEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.TableField
import com.youssef.kotlinflowts.models.joyfill.fields.table.Row
import com.youssef.kotlinflowts.models.joyfill.fields.table.TextColumn
import com.youssef.kotlinflowts.models.joyfill.utils.Document

internal class TextCellEditorImpl(
    document: Document,
    field: TableField,
    val column: TextColumn,
    val row: Row,
    onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<TableField>(document, field, onChange), TextCellEditor {

    override var value: String?
        get() = row.cells[column.id] as? String
        set(value) {
            row.cells[column.id] = value
            notifyChange(this.field.value?.map { it.toMap() }?.toMutableList())
        }
}