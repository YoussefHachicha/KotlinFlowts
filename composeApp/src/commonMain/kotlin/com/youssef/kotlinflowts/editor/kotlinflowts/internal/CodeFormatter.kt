package com.youssef.kotlinflowts.editor.kotlinflowts.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component

class CodeFormatter {
    companion object {
        private fun formatComponent(code: String, indentLevel: Int = 4): String {
            return code.lines().mapIndexed { index, line ->
                when {
                    index == 0 -> line.trim() // First line trimmed
                    line.isBlank() -> line // Keep blank lines
                    else -> " ".repeat(indentLevel) + line.trim() // Indent and trim subsequent lines
                }
            }.joinToString("\n")
        }

        fun generateContainerCode(
            containerType: Component.Type,
            components: List<ComponentEditor>,
            modifier: String = "Modifier.fillMaxWidth()",
            arrangement: String? = null
        ): String {
            // Determine arrangement based on container type if not explicitly provided
            val arrangeParam = arrangement ?: when (containerType) {
                Component.Type.row -> "horizontalArrangement = Arrangement.SpaceBetween"
                Component.Type.column -> "verticalArrangement = Arrangement.SpaceBetween"
                else -> null
            }

            // Prepare component codes with proper indentation
            val formattedComponents = components.map {
                formatComponent(it.generateCode())
            }.joinToString("\n")

            // Generate the container code with precise formatting
            return """
${containerType.name.run { this.replaceFirstChar { it.uppercase() } }}(
        modifier = $modifier,
        ${arrangeParam ?: ""},
) {
$formattedComponents
}
            """.trimIndent()
        }
    }

}
