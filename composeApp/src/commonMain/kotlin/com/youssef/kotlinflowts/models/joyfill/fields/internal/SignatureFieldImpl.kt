package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.SignatureField

@PublishedApi
internal class SignatureFieldImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedField<String>(wrapped), SignatureField