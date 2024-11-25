package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.BlockComponent

@PublishedApi
internal open class BlockComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<String>(wrapped), BlockComponent {
    override fun generateCode(): String {
        return """
            Text(
                text = "Block",
            )
        """.trimIndent()
    }
}