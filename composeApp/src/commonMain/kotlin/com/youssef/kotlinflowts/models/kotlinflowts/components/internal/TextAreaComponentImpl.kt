package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.TextAreaComponent


@PublishedApi
internal class TextAreaComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<String>(wrapped), TextAreaComponent {
    override fun generateCode(): String {
        return """
            OutlinedTextField(
                value = "$value",
                onValueChange = { },
                readOnly = $disabled,
                minLines = 5,
                colors = TextFieldDefaults.colors(),
                modifier = Modifier.fillMaxWidth()
            )
        """.trimIndent()
    }
}