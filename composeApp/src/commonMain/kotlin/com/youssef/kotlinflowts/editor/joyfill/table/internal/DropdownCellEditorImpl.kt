package com.youssef.kotlinflowts.editor.joyfill.table.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.internal.EventTrigger
import com.youssef.kotlinflowts.editor.joyfill.table.DropdownCellEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.TableField
import com.youssef.kotlinflowts.models.joyfill.fields.table.DropdownColumn
import com.youssef.kotlinflowts.models.joyfill.fields.table.Row
import com.youssef.kotlinflowts.models.joyfill.utils.Document
import com.youssef.kotlinflowts.models.joyfill.utils.Option2

internal class DropdownCellEditorImpl(
    document: Document,
    field: TableField,
    val column: DropdownColumn,
    val row: Row,
    onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<TableField>(document, field, onChange), DropdownCellEditor {

    override val options: List<Option2> = column.options

    private fun find(key: String?) = options.find { it.id == key || it.value == key }

    override fun select(key: String?) {
        val option = find(key)
        row.cells[column.id] = option?.id
        notifyChange(field.value?.map { it.toMap() }?.toMutableList())
    }

    override fun select(option: Option2?) = select(option?.id)

    override fun selected(): Option2? = find(row.cells[column.id] as? String)
}