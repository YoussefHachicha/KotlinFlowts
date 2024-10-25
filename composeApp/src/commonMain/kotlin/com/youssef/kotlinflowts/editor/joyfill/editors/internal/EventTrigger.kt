package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.events.joyfill.Target
import com.youssef.kotlinflowts.events.joyfill.toChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.Component
import com.youssef.kotlinflowts.models.joyfill.utils.App
import com.youssef.kotlinflowts.models.joyfill.utils.ID

@PublishedApi
internal open class EventTrigger<out F : Component>(
    val app: App,
    open val field: F,
    private val onChange: ((ChangeEvent) -> Unit)?
) {

    val file by lazy { app.files[0] }

    val page by lazy {
        app.files.flatMap { it.screens }.find { page ->
            val positions = page.positions.map { it.componentId }
            positions.contains(field.id)
        } ?: throw IllegalStateException("Field not found in any page")
    }

    val position by lazy {
        page.positions.find { it.componentId == field.id } ?: throw IllegalStateException("Field not found in any position")
    }

    protected fun notifyChange(value: Any?) {
        onChange?.invoke(ChangeEvent(value))
    }

    private fun ChangeEvent(value: Any?) = mutableMapOf<String, Any?>(
        ChangeEvent::changelogs.name to mutableListOf(ChangeLog(value)),
        ChangeEvent::app.name to app.toMap(),
    ).toChangeEvent()

    private fun ChangeLog(value: Any?) = mutableMapOf(
        ID to app.id,
        "identifier" to app.identifier,
        "fieldId" to field.id,
        "fieldIdentifier" to field.identifier,
        "pageId" to page.id,
        "fileId" to file.id,
        "fieldPositionId" to position.id,
        "target" to Target.field_update,
        "createdOn" to kotlinx.datetime.Clock.System.now().toEpochMilliseconds(),
        "change" to mutableMapOf(
            "value" to value,
        ),
        "sdk" to "kotlin",
        "v" to 1,
    )
}