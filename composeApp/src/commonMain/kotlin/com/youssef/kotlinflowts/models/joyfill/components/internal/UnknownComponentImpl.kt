package com.youssef.kotlinflowts.models.joyfill.components.internal

import com.youssef.kotlinflowts.models.joyfill.components.AbstractComponent
import com.youssef.kotlinflowts.models.joyfill.components.UnknownComponent

@PublishedApi
internal class UnknownComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractComponent(wrapped), UnknownComponent