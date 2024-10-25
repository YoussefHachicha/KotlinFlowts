package com.youssef.kotlinflowts.editor.joyfill.table.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.TableComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.table.RowCollection
import com.youssef.kotlinflowts.editor.joyfill.table.RowEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.fields.TableComponent
import com.youssef.kotlinflowts.models.joyfill.fields.table.DropdownColumn
import com.youssef.kotlinflowts.models.joyfill.fields.table.ImageColumn
import com.youssef.kotlinflowts.models.joyfill.fields.table.Row
import com.youssef.kotlinflowts.models.joyfill.fields.table.TextColumn
import com.youssef.kotlinflowts.models.joyfill.toRow
import com.youssef.kotlinflowts.models.joyfill.utils.App
import com.youssef.kotlinflowts.models.joyfill.utils.ID
import kotlin.math.max

@PublishedApi
internal class RowCollectionImpl(
    private val app: App,
    private val field: TableComponent,
    private val parent: TableComponentEditor,
    private val identity: IdentityGenerator,
    private val onChange: ((ChangeEvent) -> Unit)?
) : RowCollection {

    override fun all(): List<RowEditor> = field.value.map {
        RowEditorImpl(app, field, identity, it, onChange)
    }

    private fun create(index: Int): Row {
        val cells = mutableMapOf<String, Any?>()
        for (column in field.columns) when (column) {
            is TextColumn -> cells[column.id] = column.value
            is DropdownColumn -> cells[column.id] = column.value
            is ImageColumn -> cells[column.id] = column.value.map { it.toMap() }.toMutableList()
            else -> cells[column.id] = null
        }
        val row = mutableMapOf<String, Any?>(
            ID to identity.generate(),
            Row::cells.name to cells
        ).toRow()
        parent.value.add(index, row)
        field.rowOrder.add(index, row.id)
        return row
    }

    override fun addAt(index: Int): RowEditor {
        val row = create(index)
        return RowEditorImpl(app, field, identity, row, onChange)
    }

    override fun append() = addAt(field.value.size)

    override fun addAfter(index: Int) = addAt(index + 1)

    override fun addAfter(id: String): RowEditor {
        val index = field.value.indexOfFirst { it.id == id }
        return addAt(index + 1)
    }

    override fun addBefore(index: Int): RowEditor = addAt(max(index - 1, 0))

    override fun addBefore(id: String): RowEditor {
        val index = field.value.indexOfFirst { it.id == id }
        return addBefore(index)
    }

    override fun get(index: Int): RowEditor? {
        val row = field.value.getOrNull(index) ?: return null
        return RowEditorImpl(app, field, identity, row, onChange)
    }

    override fun get(id: String): RowEditor? {
        val row = field.value.find { it.id == id } ?: return null
        return RowEditorImpl(app, field, identity, row, onChange)
    }

    override fun deleteAt(index: Int): Row? = try {
        parent.value.removeAt(index)
    } catch (_: Exception) {
        null
    }
}