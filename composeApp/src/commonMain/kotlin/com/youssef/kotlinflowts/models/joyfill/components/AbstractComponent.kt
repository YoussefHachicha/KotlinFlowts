package com.youssef.kotlinflowts.models.joyfill.components

import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.type
import com.youssef.kotlinflowts.models.joyfill.utils.ID

internal abstract class AbstractComponent(
    wrapped: MutableMap<String, Any?>
) : AbstractMappable(wrapped), Component {
    override val id: String get() = wrapped[ID] as String
    override val title: String get() = wrapped[Component::title.name] as String
    override val identifier: String get() = wrapped[Component::identifier.name] as String
    override val type: Component.Type get() = wrapped.type()
    override val disabled: Boolean get() = wrapped[Component::disabled.name] as? Boolean == true
}