package com.youssef.kotlinflowts.compose.joyfill

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.youssef.kotlinflowts.editor.joyfill.editorOf
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.utils.App
import kotlinx.serialization.json.JsonObject

@Composable
fun rememberEditor(
    app: App,
    identity: IdentityGenerator = IdentityGenerator.default,
    onChange: ((ChangeEvent) -> Unit)? = null
) = remember(app, identity, onChange) {
    editorOf(app, identity, onChange)
}

@Composable
fun rememberEditor(
    map: MutableMap<String, Any?>,
    identity: IdentityGenerator = IdentityGenerator.default,
    onChange: ((ChangeEvent) -> Unit)? = null
) = remember(map, identity, onChange) {
    editorOf(map, identity, onChange)
}

@Composable
fun rememberEditor(
    json: String,
    identity: IdentityGenerator = IdentityGenerator.default,
    onChange: ((ChangeEvent) -> Unit)? = null
) = remember(json, identity, onChange) {
    editorOf(json, identity, onChange)
}

@Composable
fun rememberEditor(
    json: JsonObject,
    identity: IdentityGenerator = IdentityGenerator.default,
    onChange: ((ChangeEvent) -> Unit)? = null
) = remember(json, identity, onChange) {
    editorOf(json, identity, onChange)
}