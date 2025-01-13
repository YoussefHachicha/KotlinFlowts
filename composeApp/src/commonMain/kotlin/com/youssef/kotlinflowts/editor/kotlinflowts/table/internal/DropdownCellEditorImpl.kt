package com.youssef.kotlinflowts.editor.kotlinflowts.table.internal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.EventTrigger
import com.youssef.kotlinflowts.editor.kotlinflowts.table.DropdownCellEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.TableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.DropdownColumn
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Row
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2
import com.youssef.kotlinflowts.models.kotlinflowts.utils.option

internal class DropdownCellEditorImpl(
    app: App,
    field: TableComponent,
    val identity: IdentityGenerator,
    val column: DropdownColumn,
    val row: Row,
    onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<TableComponent>(app, field, onChange), DropdownCellEditor {

    private val _options = mutableStateListOf<Option2>()
    override val options: List<Option2> = _options

    init {
        _options.addAll(column.options)
    }

    override var selected: Option2? by mutableStateOf(selected())

    private fun find(key: String?) = options.find { it.id == key || it.value == key }

    override fun select(key: String?) {
        val option = find(key)
        row.cells[column.id] = option?.id
        selected = option
        notifyChange(component.value.map { it.toMap() }.toMutableList())
    }

    private fun String.toOption() = option(id = identity.generate(), value = this)

    override fun select(option: Option2?) = select(option?.id)

    override fun selected(): Option2? = find(row.cells[column.id] as? String)

    override fun addOption(value: String) {
        val option = value.toOption()
        if (_options.contains(option)) return
        _options.add(option)
        column.options.add(option)
        notifyChange(component.value.map { it.toMap() }.toMutableList())
    }

    override fun removeOption(id: String) {
        if (!_options.map { it.id }.contains(id)) return
        if (_options.removeIf { it.id == id }) {
            column.options.removeIf { it.id == id }
            selected = null
            notifyChange(_options.map { it.toMap() }.toMutableList())
        }
    }

}

