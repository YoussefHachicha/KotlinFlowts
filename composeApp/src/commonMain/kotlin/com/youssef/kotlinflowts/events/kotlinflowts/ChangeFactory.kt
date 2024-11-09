package com.youssef.kotlinflowts.events.kotlinflowts

import com.youssef.kotlinflowts.events.kotlinflowts.internal.ChangeEventImpl
import com.youssef.kotlinflowts.events.kotlinflowts.internal.ChangeLogImpl
import com.youssef.kotlinflowts.events.kotlinflowts.internal.FieldUpdateChangeImpl
import com.youssef.kotlinflowts.events.kotlinflowts.internal.UnknownChangeImpl

fun MutableMap<String, Any?>.toChangeLog(): ChangeLog = ChangeLogImpl(this)

fun MutableMap<String, Any?>.toChangeEvent(): ChangeEvent = ChangeEventImpl(this)

fun MutableMap<String, Any?>.toChange(): Change = FieldUpdateChangeImpl(this)

fun MutableMap<String, Any?>.toFieldChange(): FieldUpdateChange = FieldUpdateChangeImpl(this)

fun MutableMap<String, Any?>.toUnknownChange(): Change = UnknownChangeImpl(this)