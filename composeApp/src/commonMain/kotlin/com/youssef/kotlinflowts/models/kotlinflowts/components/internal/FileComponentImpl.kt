package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.FileComponent

@PublishedApi
internal class FileComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractFileBasedComponent(wrapped), FileComponent