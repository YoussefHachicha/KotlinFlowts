package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.RowComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.toComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.JsonList

@PublishedApi
internal class RowComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractListBasedComponent<Component>(wrapped), RowComponent {
    override fun factory(map: MutableMap<String, Any?>): Component = map.toComponent()

    val components = JsonList(wrapped[RowComponent::value.name]) { it.toComponent() }

    override fun generateCode(): String {
        return """
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ${components.joinToString("\n") { it.generateCode() }}
            }
        """.trimIndent()
    }
}