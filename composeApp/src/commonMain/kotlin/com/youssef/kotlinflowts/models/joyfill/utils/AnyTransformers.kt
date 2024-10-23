package com.youssef.kotlinflowts.models.joyfill.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.double
import kotlinx.serialization.json.long
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject


@OptIn(ExperimentalSerializationApi::class, ExperimentalUnsignedTypes::class)
fun Any?.toJsonElement(): JsonElement = when (this) {
    null -> JsonNull
    is JsonElement -> this
    is Boolean -> JsonPrimitive(this)
    is Number -> JsonPrimitive(this)
    is String -> JsonPrimitive(this)
    is UByte -> JsonPrimitive(this)
    is UShort -> JsonPrimitive(this)
    is UInt -> JsonPrimitive(this)
    is ULong -> JsonPrimitive(this)
    is UByteArray -> JsonArray(map { it.toJsonElement() })
    is UShortArray -> JsonArray(map { it.toJsonElement() })
    is UIntArray -> JsonArray(map { it.toJsonElement() })
    is ULongArray -> JsonArray(map { it.toJsonElement() })
    is Array<*> -> JsonArray(map { it.toJsonElement() })
    is Iterable<*> -> JsonArray(map { it.toJsonElement() })
    is Map<*, *> -> JsonObject(entries.associate { (key, value) ->
        if (key !is String) throw IllegalArgumentException("Map keys must be strings")
        key to value.toJsonElement()
    })

    else -> throw IllegalArgumentException("Unsupported type: ${this::class.simpleName}")
}

fun JsonElement.toAny(): Any? = when (this) {
    is JsonNull -> null

    is JsonPrimitive -> when {
        isBoolean -> boolean
        isString -> content
        isInteger -> long
        else /*isDouble*/ -> double
    }

    is JsonObject -> entries.associate { (key, value) -> key to value.toAny() }.toMutableMap()

    is JsonArray -> map { it.toAny() }.toMutableList()

    else -> throw IllegalArgumentException("Unsupported type: ${this::class.simpleName}")
}

fun JsonObject.toMap() = entries.associate { (key, value) -> key to value.toAny() }.toMutableMap()

fun Map<String,Any?>.toJsonObject() = JsonObject(entries.associate { (key, value) -> key to value.toJsonElement() })

val JsonPrimitive.isBoolean get() = !isString && content in setOf("true", "false")

val JsonPrimitive.isInteger get() = !isString && content.all { it in '0'..'9' || it == '-' }

inline val Any.asString: String get() = if (this is String) this else error("Expected a string, but got $this")