package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.AbstractComponent
import com.youssef.kotlinflowts.models.joyfill.fields.UnknownComponent

@PublishedApi
internal class UnknownComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractComponent(wrapped), UnknownComponent