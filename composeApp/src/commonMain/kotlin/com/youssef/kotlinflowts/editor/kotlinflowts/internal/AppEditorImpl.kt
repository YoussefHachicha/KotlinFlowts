package com.youssef.kotlinflowts.editor.kotlinflowts.internal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.youssef.kotlinflowts.editor.kotlinflowts.Valid
import com.youssef.kotlinflowts.editor.kotlinflowts.Validation
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.AppEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.Stage
import com.youssef.kotlinflowts.models.kotlinflowts.MutableApp
import com.youssef.kotlinflowts.models.kotlinflowts.View
import com.youssef.kotlinflowts.models.kotlinflowts.toMutableApp
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ComponentId
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID

internal class AppEditorImpl(
    private val app: MutableApp,
    private val identity: IdentityGenerator,
    private val onChange: ((ChangeEvent) -> Unit)?,
) : AppEditor {

    constructor(
        app: App,
        identity: IdentityGenerator,
        onChange: ((ChangeEvent) -> Unit)?,
    ) : this(app.toMutableApp(), identity, onChange)

    companion object {
        const val STAGE = "stage"
    }

    override var stage: Stage
        get() = get<String?>(STAGE)?.let { Stage.valueOf(it) } ?: Stage.draft
        set(value) = set(STAGE, value.name)

    override var name: String
        get() = app.name
        set(value) {
            app.name = value
        }

    override var id: String
        get() = app.id
        set(value) = set(ID, value)

    override var identifier: String
        get() = get(App::identifier.name)
        set(value) = set(App::identifier.name, value)

    override val components by lazy { CompCollectionImpl(app, identity, onChange) }

    override val screens by lazy { ScreenCollectionImpl(app) }

    override val views: List<View> get() = app.files.flatMap { it.views }

    override fun set(key: String, value: Any?) = app.set(key, value)

    override fun <R> get(key: String): R = app.get(key)

    override fun integrity(): Validation {
        return Valid(emptyList())
    }

    override fun validate(): Validation {
        return Valid(emptyList())
    }

    override fun toMap() = app.toMap()

    override fun toApp(): App = app

    override var selectedEditorComponent: ComponentEditor? by mutableStateOf(null)

    override fun toJsonObject() = app.toJsonObject()

    override fun toJsonString() = app.toJsonString()
}