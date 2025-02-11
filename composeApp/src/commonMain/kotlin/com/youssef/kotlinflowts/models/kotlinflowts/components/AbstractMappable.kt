package com.youssef.kotlinflowts.models.kotlinflowts.components

import com.youssef.kotlinflowts.models.kotlinflowts.Mappable
import com.youssef.kotlinflowts.models.kotlinflowts.utils.toJsonObject
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

abstract class AbstractMappable(
    val wrapped: MutableMap<String, Any?>
) : Mappable {
    override fun toMap(): MutableMap<String, Any?> = wrapped

    override fun toJsonString(): String = Json.encodeToString(JsonObject.serializer(), toJsonObject())

    override fun toJsonObject(): JsonObject = toMap().toJsonObject()
}