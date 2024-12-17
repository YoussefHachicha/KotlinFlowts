package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.TextFieldComponent

@PublishedApi
internal class TextFieldComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<String>(wrapped), TextFieldComponent