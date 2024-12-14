package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import androidx.compose.runtime.mutableStateListOf
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.FileBasedComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ListBasedComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Attachment
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID
import com.youssef.kotlinflowts.models.kotlinflowts.utils.toAttachment

@PublishedApi
internal abstract class AbstractFileBasedComponentEditor(
    app: App,
    override val comp: ListBasedComponent<Attachment>,
    private val identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractListBasedComponentEditor<Attachment>(app, comp, onChange), FileBasedComponentEditor {

    private val _value = mutableStateListOf<Attachment>()
    override val fileValue: List<Attachment> = _value

    override fun add(url: String) = add(listOf(url))

    override fun add(urls: List<String>) {
        val attachments = urls.map {
            mutableMapOf<String, Any?>(
                ID to identity.generate(),
                Attachment::url.name to it
            ).toAttachment()
        }.toMutableList()
        _value.addAll(attachments)
        println("add Image: ${fileValue.size}. urls:${attachments.map { it.url }}")
    }

    override fun set(urls: List<String>) {
        val attachments = urls.map {
            mutableMapOf<String, Any?>(
                ID to identity.generate(),
                Attachment::url.name to it
            ).toAttachment()
        }.toMutableList()
        comp.value.clear()
        _value.addAll(attachments)
    }

    override fun remove(key: String?) {
        if(key==null) return
        remove(listOf(key))
    }

    override fun remove(keys: List<String>) {
        val found = comp.value.filter { it.id in keys || it.url in keys || it.fileName in keys }
        if(found.isEmpty()) return
        _value.removeAll(found)
    }

    override fun clear() {
        _value.clear()
    }
}