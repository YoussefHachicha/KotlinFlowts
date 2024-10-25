package com.youssef.kotlinflowts.editor.joyfill.table.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.internal.EventTrigger
import com.youssef.kotlinflowts.editor.joyfill.table.TextCellEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.TableComponent
import com.youssef.kotlinflowts.models.joyfill.fields.table.Row
import com.youssef.kotlinflowts.models.joyfill.fields.table.TextColumn
import com.youssef.kotlinflowts.models.joyfill.utils.App

internal class TextCellEditorImpl(
    app: App,
    field: TableComponent,
    val column: TextColumn,
    val row: Row,
    onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<TableComponent>(app, field, onChange), TextCellEditor {

    override var value: String?
        get() = row.cells[column.id] as? String
        set(value) {
            row.cells[column.id] = value
            notifyChange(this.field.value?.map { it.toMap() }?.toMutableList())
        }
}