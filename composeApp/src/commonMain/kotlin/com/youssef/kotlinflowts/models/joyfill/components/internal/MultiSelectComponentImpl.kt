package com.youssef.kotlinflowts.models.joyfill.components.internal

import com.youssef.kotlinflowts.models.joyfill.components.AbstractComponent
import com.youssef.kotlinflowts.models.joyfill.components.DropdownComponent
import com.youssef.kotlinflowts.models.joyfill.components.MultiSelectComponent
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList
import com.youssef.kotlinflowts.models.joyfill.utils.Option2
import com.youssef.kotlinflowts.models.joyfill.utils.toOption

@PublishedApi
internal open class MultiSelectComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractComponent(wrapped), MultiSelectComponent {
    override val options: List<Option2> = JsonList(wrapped[DropdownComponent::options.name]) { it.toOption() }
    override val value: MutableList<String> get() = wrapped[MultiSelectComponent::value.name] as MutableList<String>
}