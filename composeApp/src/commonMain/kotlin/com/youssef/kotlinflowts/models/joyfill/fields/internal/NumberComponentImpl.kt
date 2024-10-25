package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.NumberComponent

@PublishedApi
internal class NumberComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<Double>(wrapped), NumberComponent