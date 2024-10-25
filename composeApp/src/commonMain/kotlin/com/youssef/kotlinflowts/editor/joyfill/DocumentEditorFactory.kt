package com.youssef.kotlinflowts.editor.joyfill

import com.youssef.kotlinflowts.editor.joyfill.editors.AppEditor
import com.youssef.kotlinflowts.editor.joyfill.internal.AppEditorImpl
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.toApp
import com.youssef.kotlinflowts.models.joyfill.utils.App
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
): AppEditor {
    val doc = Json.decodeFromString(JsonObject.serializer(), json)
    return AppEditorImpl(doc.toApp(), identity, onChange)
}

fun editorOf(
    json: JsonObject,
    identity: IdentityGenerator = DEFAULT_GENERATOR,
    onChange: ((ChangeEvent) -> Unit)? = null
): AppEditor = AppEditorImpl(json.toApp(), identity, onChange)

fun editorOf(
    map: MutableMap<String, Any?>,
    identity: IdentityGenerator = DEFAULT_GENERATOR,
    onChange: ((ChangeEvent) -> Unit)? = null
): AppEditor = AppEditorImpl(map.toApp(), identity, onChange)

fun editorOf(
    app: App,
    identity: IdentityGenerator = DEFAULT_GENERATOR,
    onChange: ((ChangeEvent) -> Unit)? = null
): AppEditor = AppEditorImpl(app, identity, onChange)