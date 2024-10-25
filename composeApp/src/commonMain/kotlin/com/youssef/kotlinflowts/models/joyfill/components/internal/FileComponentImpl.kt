package com.youssef.kotlinflowts.models.joyfill.components.internal

import com.youssef.kotlinflowts.models.joyfill.components.FileComponent

@PublishedApi
internal class FileComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractFileBasedComponent(wrapped), FileComponent