package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.FileComponent

@PublishedApi
internal class FileComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractFileBasedComponent(wrapped), FileComponent