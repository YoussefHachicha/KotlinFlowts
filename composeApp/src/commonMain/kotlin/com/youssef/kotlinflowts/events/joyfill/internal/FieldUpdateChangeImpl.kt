package com.youssef.kotlinflowts.events.joyfill.internal

import com.youssef.kotlinflowts.events.joyfill.FieldUpdateChange

internal class FieldUpdateChangeImpl(val wrapped: Map<String, Any?>) : FieldUpdateChange {
    override val value get() = wrapped[FieldUpdateChange::value.name]
}