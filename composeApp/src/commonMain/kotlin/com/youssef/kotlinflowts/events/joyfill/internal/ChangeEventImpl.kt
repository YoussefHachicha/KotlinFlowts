package com.youssef.kotlinflowts.events.joyfill.internal

import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.events.joyfill.ChangeLog
import com.youssef.kotlinflowts.events.joyfill.toChangeLog
import com.youssef.kotlinflowts.models.joyfill.fields.AbstractMappable
import com.youssef.kotlinflowts.models.joyfill.toDocument
import com.youssef.kotlinflowts.models.joyfill.utils.Document
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList
import com.youssef.kotlinflowts.models.joyfill.utils.toJsonObject
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

internal class ChangeEventImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractMappable(wrapped), ChangeEvent {

    override val changelogs: List<ChangeLog> = JsonList(wrapped[ChangeEvent::changelogs.name]) { it.toChangeLog() }

    override val document: Document = (wrapped[ChangeEvent::document.name] as MutableMap<String, Any?>).toDocument()

    override fun toMap() = wrapped

    override fun toJsonString(): String = Json.encodeToString(toJsonObject())

    override fun toJsonObject(): JsonObject = wrapped.toJsonObject()
}