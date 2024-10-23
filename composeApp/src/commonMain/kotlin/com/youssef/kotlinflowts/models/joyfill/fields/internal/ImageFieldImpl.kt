package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.ImageField

@PublishedApi
internal class ImageFieldImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractFileBasedField(wrapped), ImageField