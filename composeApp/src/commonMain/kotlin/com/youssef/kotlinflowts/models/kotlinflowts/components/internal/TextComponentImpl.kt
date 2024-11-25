package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.TextComponent

@PublishedApi
internal class TextComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<String>(wrapped), TextComponent {
    override fun generateCode(): String {
        return """
            OutlinedTextField(
                value = "$value",
                onValueChange = { },
                readOnly = $disabled,
                singleLine = true,
                maxLines = 1,
                colors = OutlinedTextFieldDefaults.colors(),
                modifier = Modifier.fillMaxWidth()
            )
        """.trimIndent()
    }
}
