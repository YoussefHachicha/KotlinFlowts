package com.youssef.kotlinflowts.models.kotlinflowts

import kotlinx.serialization.json.JsonObject

interface Mappable {
    fun toMap(): MutableMap<String, Any?>
    fun toJsonString(): String
    fun toJsonObject(): JsonObject
}