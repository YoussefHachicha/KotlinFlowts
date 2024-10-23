package com.youssef.kotlinflowts.models.joyfill.internal

import com.youssef.kotlinflowts.models.joyfill.Document
import com.youssef.kotlinflowts.models.joyfill.MutableDocument
import com.youssef.kotlinflowts.models.joyfill.MutableFile
import com.youssef.kotlinflowts.models.joyfill.fields.AbstractMappable
import com.youssef.kotlinflowts.models.joyfill.toDocument
import com.youssef.kotlinflowts.models.joyfill.toField
import com.youssef.kotlinflowts.models.joyfill.utils.ID
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList

@PublishedApi
internal class DocumentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractMappable(wrapped), MutableDocument {
    override var name: String
        get() = wrapped[Document::name.name] as String
        set(value) {
            wrapped[Document::name.name] = value
        }

    override val id: String get() = wrapped[ID] as String

    override val identifier: String get() = wrapped[Document::identifier.name] as String

    override val files: MutableList<MutableFile> = JsonList(wrapped[Document::files.name]) { FileImpl(it) }

    override val fields = JsonList(wrapped[Document::fields.name]) { it.toField() }

    override fun <R> get(key: String): R = wrapped[key] as R

    override fun set(key: String, value: Any?) {
        wrapped[key] = value
    }

    override fun copy(): Document  = toJsonString().toDocument()
}