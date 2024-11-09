package com.youssef.kotlinflowts.editor.kotlinflowts.table.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.EventTrigger
import com.youssef.kotlinflowts.editor.kotlinflowts.table.TextCellEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Row
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.TextColumn
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

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
            notifyChange(this.component.value?.map { it.toMap() }?.toMutableList())
        }
}