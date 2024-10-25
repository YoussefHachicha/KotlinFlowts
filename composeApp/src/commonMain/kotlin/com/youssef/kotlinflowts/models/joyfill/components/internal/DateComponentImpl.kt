package com.youssef.kotlinflowts.models.joyfill.components.internal

import com.youssef.kotlinflowts.models.joyfill.components.DateComponent

@PublishedApi
internal open class DateComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<Long>(wrapped), DateComponent {
    override val format: String? get() = wrapped["format"] as String?
}

