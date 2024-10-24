package com.youssef.kotlinflowts.editor.joyfill.internal

import com.youssef.kotlinflowts.editor.joyfill.Valid
import com.youssef.kotlinflowts.editor.joyfill.Validation
import com.youssef.kotlinflowts.editor.joyfill.editors.DocumentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.JoyStage
import com.youssef.kotlinflowts.models.joyfill.MutableDocument
import com.youssef.kotlinflowts.models.joyfill.View
import com.youssef.kotlinflowts.models.joyfill.toMutableDocument
import com.youssef.kotlinflowts.models.joyfill.utils.Document
import com.youssef.kotlinflowts.models.joyfill.utils.ID

internal class DocumentEditorImpl(
    private val document: MutableDocument,
    private val identity: IdentityGenerator,
    private val onChange: ((ChangeEvent) -> Unit)?,
) : DocumentEditor {

    constructor(
        document: Document,
        identity: IdentityGenerator,
        onChange: ((ChangeEvent) -> Unit)?,
    ) : this(document.toMutableDocument(), identity, onChange)

    companion object {
        const val STAGE = "stage"
    }

    override var stage: JoyStage
        get() = get<String?>(STAGE)?.let { JoyStage.valueOf(it) } ?: JoyStage.draft
        set(value) = set(STAGE, value.name)

    override var name: String
        get() = document.name
        set(value) {
            document.name = value
        }

    override var id: String
        get() = document.id
        set(value) = set(ID, value)

    override var identifier: String
        get() = get(Document::identifier.name)
        set(value) = set(Document::identifier.name, value)

    override val fields by lazy { FieldCollectionImpl(document, identity, onChange) }

    override val pages by lazy { PageCollectionImpl(document) }

    override val views: List<View> get() = document.files.flatMap { it.views }

    override fun set(key: String, value: Any?) = document.set(key, value)

    override fun <R> get(key: String): R = document.get(key)

    override fun integrity(): Validation {
        return Valid(emptyList())
    }

    override fun validate(): Validation {
        return Valid(emptyList())
    }

    override fun toMap() = document.toMap()

    override fun toDocument(): Document = document

    override fun toJsonObject() = document.toJsonObject()

    override fun toJsonString() = document.toJsonString()
}