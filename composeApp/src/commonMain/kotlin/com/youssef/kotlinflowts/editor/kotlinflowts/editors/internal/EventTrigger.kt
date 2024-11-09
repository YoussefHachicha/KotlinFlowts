package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.events.kotlinflowts.Target
import com.youssef.kotlinflowts.events.kotlinflowts.toChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID

@PublishedApi
internal open class EventTrigger<out C : Component>(
    val app: App,
    open val component: C,
    private val onChange: ((ChangeEvent) -> Unit)?
) {

    val file by lazy { app.files[0] }

    val screen by lazy {
        app.files.flatMap { it.screens }.find { page ->
            val positions = page.positions.map { it.componentId }
            positions.contains(component.id)
        } ?: throw IllegalStateException("Component not found in any screen")
    }

    val position by lazy {
        screen.positions.find { it.componentId == component.id } ?: throw IllegalStateException("Field not found in any position")
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
        "componentId" to component.id,
        "componentIdentifier" to component.identifier,
        "screenId" to screen.id,
        "fileId" to file.id,
        "componentPositionId" to position.id,
        "target" to Target.field_update,
        "createdOn" to kotlinx.datetime.Clock.System.now().toEpochMilliseconds(),
        "change" to mutableMapOf(
            "value" to value,
        ),
        "sdk" to "kotlin",
        "v" to 1,
    )
}