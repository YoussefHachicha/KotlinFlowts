package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.TextField

@PublishedApi
internal class TextFieldImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedField<String>(wrapped), TextField