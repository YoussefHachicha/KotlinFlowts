package com.youssef.kotlinflowts.events.kotlinflowts.internal

import com.youssef.kotlinflowts.events.kotlinflowts.FieldUpdateChange

internal class FieldUpdateChangeImpl(val wrapped: Map<String, Any?>) : FieldUpdateChange {
    override val value get() = wrapped[FieldUpdateChange::value.name]
}