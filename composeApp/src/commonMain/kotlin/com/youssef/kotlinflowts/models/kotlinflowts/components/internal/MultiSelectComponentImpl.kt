package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.AbstractComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.DropdownComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.MultiSelectComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.JsonList
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2
import com.youssef.kotlinflowts.models.kotlinflowts.utils.toOption

@PublishedApi
internal open class MultiSelectComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractComponent(wrapped), MultiSelectComponent {
    override val options: List<Option2> = JsonList(wrapped[DropdownComponent::options.name]) { it.toOption() }
    override val value: MutableList<String> get() = wrapped[MultiSelectComponent::value.name] as MutableList<String>

    override fun generateCode(): String {
        return """
            Text(
                text = "Multi Select",
            )
        """.trimIndent()
    }
}