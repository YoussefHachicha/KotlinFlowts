package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.youssef.kotlinflowts.editor.kotlinflowts.editorOf
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
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