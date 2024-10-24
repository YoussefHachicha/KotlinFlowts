package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.events.joyfill.Target
import com.youssef.kotlinflowts.events.joyfill.toChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.Field
import com.youssef.kotlinflowts.models.joyfill.utils.Document
import com.youssef.kotlinflowts.models.joyfill.utils.ID

@PublishedApi
internal open class EventTrigger<out F : Field>(
    val document: Document,
    open val field: F,
    private val onChange: ((ChangeEvent) -> Unit)?
) {

    val file by lazy { document.files[0] }

    val page by lazy {
        document.files.flatMap { it.pages }.find { page ->
            val positions = page.positions.map { it.field }
            positions.contains(field.id)
        } ?: throw IllegalStateException("Field not found in any page")
    }

    val position by lazy {
        page.positions.find { it.field == field.id } ?: throw IllegalStateException("Field not found in any position")
    }

    protected fun notifyChange(value: Any?) {
        onChange?.invoke(ChangeEvent(value))
    }

    private fun ChangeEvent(value: Any?) = mutableMapOf<String, Any?>(
        ChangeEvent::changelogs.name to mutableListOf(ChangeLog(value)),
        ChangeEvent::document.name to document.toMap(),
    ).toChangeEvent()

    private fun ChangeLog(value: Any?) = mutableMapOf(
        ID to document.id,
        "identifier" to document.identifier,
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