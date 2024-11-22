package com.youssef.kotlinflowts.models.kotlinflowts.components

import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.MutableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.type
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID

internal abstract class AbstractComponent(
    wrapped: MutableMap<String, Any?>
) : AbstractMappable(wrapped), MutableComponent {
    override val id: String get() = wrapped[ID] as String
    override var title: String get() = wrapped[Component::title.name] as String
        set(value) {
            wrapped[Component::title.name] = value
        }
    override val identifier: String get() = wrapped[Component::identifier.name] as String
    override val type: Component.Type get() = wrapped.type()
    override var disabled: Boolean get() = wrapped[Component::disabled.name] as? Boolean == true
        set(value) {
            wrapped[Component::disabled.name] = value
        }
    override val depth: Int get() = wrapped[Component::depth.name] as Int
}