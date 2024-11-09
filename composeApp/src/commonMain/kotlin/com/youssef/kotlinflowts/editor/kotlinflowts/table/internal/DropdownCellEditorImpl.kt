package com.youssef.kotlinflowts.editor.kotlinflowts.table.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.EventTrigger
import com.youssef.kotlinflowts.editor.kotlinflowts.table.DropdownCellEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.DropdownColumn
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Row
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2

internal class DropdownCellEditorImpl(
    app: App,
    field: TableComponent,
    val column: DropdownColumn,
    val row: Row,
    onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<TableComponent>(app, field, onChange), DropdownCellEditor {

    override val options: List<Option2> = column.options

    private fun find(key: String?) = options.find { it.id == key || it.value == key }

    override fun select(key: String?) {
        val option = find(key)
        row.cells[column.id] = option?.id
        notifyChange(component.value?.map { it.toMap() }?.toMutableList())
    }

    override fun select(option: Option2?) = select(option?.id)

    override fun selected(): Option2? = find(row.cells[column.id] as? String)
}