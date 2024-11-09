package com.youssef.kotlinflowts.editor.kotlinflowts.internal

internal inline val MutableMap<String, Any?>.files get() = this["files"] as List<MutableMap<String, Any?>>
internal inline val MutableMap<String, Any?>.fields get() = this["fields"] as List<MutableMap<String, Any?>>
internal inline val MutableMap<String, Any?>.pages get() = this["pages"] as List<MutableMap<String, Any?>>

internal inline val MutableMap<String, Any?>.positions get() = this["fieldPositions"] as List<MutableMap<String, Any?>>

internal inline val MutableMap<String, Any?>.field get() = this["field"] as String

private const val ID = "_id"
internal inline var MutableMap<String, Any?>.id: String
    get() = this[ID] as String
    set(value) {
        this[ID] = value
    }

private const val IDENTIFIER = "identifier"
internal inline var MutableMap<String, Any?>.identifier: String
    get() = this[IDENTIFIER] as String
    set(value) {
        this[IDENTIFIER] = value
    }

private const val NAME = "name"
internal inline var MutableMap<String, Any?>.name: String
    get() = this[NAME] as String
    set(value) {
        this[NAME] = value
    }

private const val TITLE = "title"
internal inline var MutableMap<String, Any?>.title: String
    get() = this[TITLE] as String
    set(value) {
        this[TITLE] = value
    }

private const val DESCRIPTION = "description"
internal inline var MutableMap<String, Any?>.description: String
    get() = this[DESCRIPTION] as String
    set(value) {
        this[DESCRIPTION] = value
    }

private const val TYPE = "type"
internal inline var MutableMap<String, Any?>.type: String
    get() = this[TYPE] as String
    set(value) {
        this[TYPE] = value
    }