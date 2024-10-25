package com.youssef.kotlinflowts.models.joyfill.components.internal

import com.youssef.kotlinflowts.models.joyfill.components.NumberComponent

@PublishedApi
internal class NumberComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<Double>(wrapped), NumberComponent