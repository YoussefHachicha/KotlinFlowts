package com.youssef.kotlinflowts.editor.kotlinflowts.table.internal

import androidx.compose.runtime.mutableStateListOf
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.EventTrigger
import com.youssef.kotlinflowts.editor.kotlinflowts.table.ImageCellEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.TableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.ImageColumn
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Row
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Attachment
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import com.youssef.kotlinflowts.models.kotlinflowts.utils.JsonList
import com.youssef.kotlinflowts.models.kotlinflowts.utils.toAttachment

internal class ImageCellEditorImpl(
    app: App,
    field: TableComponent,
    val identity: IdentityGenerator,
    override val column: ImageColumn,
    row: Row,
    onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<TableComponent>(app, field, onChange), ImageCellEditor {

    private val _value =  mutableStateListOf<Attachment>()
    override val fileValue: List<Attachment> = _value

    init {
        _value.addAll(JsonList(row.cells[column.id]) { it.toAttachment() })
    }

    override fun add(url: String) = add(listOf(url))

    override fun add(urls: List<String>) {
        val attachments = urls.map { Attachment(id = identity.generate(), it) }
        println("add Image: ${_value.size}. urls:${attachments.map { it.url }}")


        _value.addAll(attachments)
        notifyChange()
    }

    override fun set(urls: List<String>) {
        val attachments = urls.map { Attachment(id = identity.generate(), it) }
        _value.clear()
        _value.addAll(attachments)
        notifyChange()
    }

    override fun remove(key: String?) {
        if (key == null) return
        remove(listOf(key))
    }

    override fun remove(keys: List<String>) {
        val found = fileValue.filter { it.id in keys || it.url in keys || it.fileName in keys }
        if (found.isEmpty()) return
        _value.removeAll(found)
        notifyChange()
    }

    private fun notifyChange() {
        notifyChange(component.value.map { it.toMap() }.toMutableList())
    }

    override fun clear() {
        _value.clear()
        notifyChange()
    }
}