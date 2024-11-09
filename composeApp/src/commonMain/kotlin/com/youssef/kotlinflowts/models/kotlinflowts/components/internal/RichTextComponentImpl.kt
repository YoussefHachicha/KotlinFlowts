package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.RichTextComponent

@PublishedApi
internal open class RichTextComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<String>(wrapped), RichTextComponent