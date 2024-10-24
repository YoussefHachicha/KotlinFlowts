package com.youssef.kotlinflowts.events.joyfill

import com.youssef.kotlinflowts.events.joyfill.internal.ChangeEventImpl
import com.youssef.kotlinflowts.events.joyfill.internal.ChangeLogImpl
import com.youssef.kotlinflowts.events.joyfill.internal.FieldUpdateChangeImpl
import com.youssef.kotlinflowts.events.joyfill.internal.UnknownChangeImpl

fun MutableMap<String, Any?>.toChangeLog(): ChangeLog = ChangeLogImpl(this)

fun MutableMap<String, Any?>.toChangeEvent(): ChangeEvent = ChangeEventImpl(this)

fun MutableMap<String, Any?>.toChange(): Change = FieldUpdateChangeImpl(this)

fun MutableMap<String, Any?>.toFieldChange(): FieldUpdateChange = FieldUpdateChangeImpl(this)

fun MutableMap<String, Any?>.toUnknownChange(): Change = UnknownChangeImpl(this)