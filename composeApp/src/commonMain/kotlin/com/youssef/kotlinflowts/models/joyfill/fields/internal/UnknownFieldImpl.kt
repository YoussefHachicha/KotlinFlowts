package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.AbstractField
import com.youssef.kotlinflowts.models.joyfill.fields.UnknownField

@PublishedApi
internal class UnknownFieldImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractField(wrapped), UnknownField