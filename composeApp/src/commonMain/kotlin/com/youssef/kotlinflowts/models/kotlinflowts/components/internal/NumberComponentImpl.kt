package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.NumberComponent

@PublishedApi
internal class NumberComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<Double>(wrapped), NumberComponent {
    override fun generateCode(): String {
        return """
            OutlinedTextField(
                value = value,
                onValueChange = { },
                readOnly = ${this.disabled},
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        """.trimIndent()
    }
}