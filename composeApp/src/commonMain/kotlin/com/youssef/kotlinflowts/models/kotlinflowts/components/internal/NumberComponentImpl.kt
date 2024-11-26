package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.NumberComponent

@PublishedApi
internal class NumberComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<Double>(wrapped), NumberComponent {

}