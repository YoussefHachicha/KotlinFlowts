package com.youssef.kotlinflowts.editor.joyfill.table.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.internal.EventTrigger
import com.youssef.kotlinflowts.editor.joyfill.table.ImageCellEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.fields.TableComponent
import com.youssef.kotlinflowts.models.joyfill.fields.table.ImageColumn
import com.youssef.kotlinflowts.models.joyfill.fields.table.Row
import com.youssef.kotlinflowts.models.joyfill.utils.Attachment
import com.youssef.kotlinflowts.models.joyfill.utils.App
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList
import com.youssef.kotlinflowts.models.joyfill.utils.toAttachment

internal class ImageCellEditorImpl(
    app: App,
    field: TableComponent,
    val identity: IdentityGenerator,
    override val column: ImageColumn,
    row: Row,
    onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<TableComponent>(app, field, onChange), ImageCellEditor {
    override val value: MutableList<Attachment> = JsonList(row.cells[column.id]) { it.toAttachment() }

    override fun add(url: String) = add(listOf(url))

    override fun add(urls: List<String>) {
        val attachments = urls.map { Attachment(id = identity.generate(), it) }
        value.addAll(attachments)
        notifyChange()
    }

    override fun set(urls: List<String>) {
        val attachments = urls.map { Attachment(id = identity.generate(), it) }
        value.clear()
        value.addAll(attachments)
        notifyChange()
    }

    override fun remove(key: String?) {
        if (key == null) return
        remove(listOf(key))
    }

    override fun remove(keys: List<String>) {
        val found = value.filter { it.id in keys || it.url in keys || it.fileName in keys }
        if (found.isEmpty()) return
        value.removeAll(found)
        notifyChange()
    }

    private fun notifyChange() {
        notifyChange(field.value.map { it.toMap() }.toMutableList())
    }
}