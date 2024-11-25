package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.AbstractComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.UnknownComponent

@PublishedApi
internal class UnknownComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractComponent(wrapped), UnknownComponent {
    override fun generateCode(): String {
        return """
            Text("Unknown component")
        """.trimIndent()
    }
}