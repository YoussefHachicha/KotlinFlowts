package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.FileField

@PublishedApi
internal class FileFieldImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractFileBasedField(wrapped), FileField