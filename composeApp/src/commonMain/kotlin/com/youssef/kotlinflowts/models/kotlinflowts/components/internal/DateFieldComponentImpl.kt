package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.DateFieldComponent

@PublishedApi
internal open class DateFieldComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<Long>(wrapped), DateFieldComponent {
    override val format: String? get() = wrapped["format"] as String?
}

