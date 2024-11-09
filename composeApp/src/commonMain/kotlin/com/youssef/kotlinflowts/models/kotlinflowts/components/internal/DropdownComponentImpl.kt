package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.DropdownComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.JsonList
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2
import com.youssef.kotlinflowts.models.kotlinflowts.utils.toOption

@PublishedApi
internal open class DropdownComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<String>(wrapped), DropdownComponent {
    override val options: List<Option2> = JsonList(wrapped[DropdownComponent::options.name]) { it.toOption() }
}