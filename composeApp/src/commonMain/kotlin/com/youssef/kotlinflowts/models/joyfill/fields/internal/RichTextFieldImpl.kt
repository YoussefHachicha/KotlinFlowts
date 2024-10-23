package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.RichTextField

@PublishedApi
internal open class RichTextFieldImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedField<String>(wrapped), RichTextField