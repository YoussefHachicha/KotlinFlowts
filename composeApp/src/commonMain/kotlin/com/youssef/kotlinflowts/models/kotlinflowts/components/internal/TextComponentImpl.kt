package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.TextComponent

@PublishedApi
internal class TextComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<String>(wrapped), TextComponent