package com.youssef.kotlinflowts.editor.joyfill

import com.youssef.kotlinflowts.editor.joyfill.editors.DocumentEditor
import com.youssef.kotlinflowts.editor.joyfill.internal.DocumentEditorImpl
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.toDocument
import com.youssef.kotlinflowts.models.joyfill.utils.Document
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

internal val DEFAULT_CODEC = Json {
    prettyPrint = true
    prettyPrintIndent = "  "
}

internal val DEFAULT_GENERATOR = IdentityGenerator.default

fun editorOf(
    json: String,
    identity: IdentityGenerator = DEFAULT_GENERATOR,
    onChange: ((ChangeEvent) -> Unit)? = null
): DocumentEditor {
    val doc = Json.decodeFromString(JsonObject.serializer(), json)
    return DocumentEditorImpl(doc.toDocument(), identity, onChange)
}

fun editorOf(
    json: JsonObject,
    identity: IdentityGenerator = DEFAULT_GENERATOR,
    onChange: ((ChangeEvent) -> Unit)? = null
): DocumentEditor = DocumentEditorImpl(json.toDocument(), identity, onChange)

fun editorOf(
    map: MutableMap<String, Any?>,
    identity: IdentityGenerator = DEFAULT_GENERATOR,
    onChange: ((ChangeEvent) -> Unit)? = null
): DocumentEditor = DocumentEditorImpl(map.toDocument(), identity, onChange)

fun editorOf(
    document: Document,
    identity: IdentityGenerator = DEFAULT_GENERATOR,
    onChange: ((ChangeEvent) -> Unit)? = null
): DocumentEditor = DocumentEditorImpl(document, identity, onChange)