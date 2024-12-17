package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.NumberFieldComponent

@PublishedApi
internal class NumberFieldComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<Double>(wrapped), NumberFieldComponent {

}