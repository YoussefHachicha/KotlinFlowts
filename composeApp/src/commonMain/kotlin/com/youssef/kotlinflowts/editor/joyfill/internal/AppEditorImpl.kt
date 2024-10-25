package com.youssef.kotlinflowts.editor.joyfill.internal

import com.youssef.kotlinflowts.editor.joyfill.Valid
import com.youssef.kotlinflowts.editor.joyfill.Validation
import com.youssef.kotlinflowts.editor.joyfill.editors.AppEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.JoyStage
import com.youssef.kotlinflowts.models.joyfill.MutableApp
import com.youssef.kotlinflowts.models.joyfill.View
import com.youssef.kotlinflowts.models.joyfill.toMutableApp
import com.youssef.kotlinflowts.models.joyfill.utils.App
import com.youssef.kotlinflowts.models.joyfill.utils.ID

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

    override var stage: JoyStage
        get() = get<String?>(STAGE)?.let { JoyStage.valueOf(it) } ?: JoyStage.draft
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

    override fun toJsonObject() = app.toJsonObject()

    override fun toJsonString() = app.toJsonString()
}