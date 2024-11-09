package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.DateComponent

@PublishedApi
internal open class DateComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<Long>(wrapped), DateComponent {
    override val format: String? get() = wrapped["format"] as String?
}

