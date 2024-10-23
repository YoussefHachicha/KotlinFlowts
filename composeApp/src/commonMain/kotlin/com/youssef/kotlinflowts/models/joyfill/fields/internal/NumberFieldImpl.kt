package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.NumberField

@PublishedApi
internal class NumberFieldImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedField<Double>(wrapped), NumberField