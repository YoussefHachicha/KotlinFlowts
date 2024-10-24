package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.FileBasedFieldEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.fields.ListBasedField
import com.youssef.kotlinflowts.models.joyfill.utils.Attachment
import com.youssef.kotlinflowts.models.joyfill.utils.Document
import com.youssef.kotlinflowts.models.joyfill.utils.ID
import com.youssef.kotlinflowts.models.joyfill.utils.toAttachment

@PublishedApi
internal abstract class AbstractFileBasedFieldEditor(
    document: Document,
    override val field: ListBasedField<Attachment>,
    private val identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractListBasedFieldEditor<Attachment>(document, field, onChange), FileBasedFieldEditor {

    override fun add(url: String) = add(listOf(url))

    override fun add(urls: List<String>) {
        val attachments = urls.map {
            mutableMapOf<String, Any?>(
                ID to identity.generate(),
                Attachment::url.name to it
            ).toAttachment()
        }.toMutableList()
        value.addAll(attachments)
    }

    override fun set(urls: List<String>) {
        val attachments = urls.map {
            mutableMapOf<String, Any?>(
                ID to identity.generate(),
                Attachment::url.name to it
            ).toAttachment()
        }.toMutableList()
        field.value.clear()
        value.addAll(attachments)
    }

    override fun remove(key: String?) {
        if(key==null) return
        remove(listOf(key))
    }

    override fun remove(keys: List<String>) {
        val found = field.value.filter { it.id in keys || it.url in keys || it.fileName in keys }
        if(found.isEmpty()) return
        value.removeAll(found)
    }
}