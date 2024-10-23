package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.DateField

@PublishedApi
internal open class DateFieldImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedField<Long>(wrapped), DateField {
    override val format: String? get() = wrapped["format"] as String?
}

