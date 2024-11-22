package com.youssef.kotlinflowts.models.kotlinflowts.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.type
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID

internal abstract class AbstractComponent(
    wrapped: MutableMap<String, Any?>
) : AbstractMappable(wrapped), Component {
    override val id: String get() = wrapped[ID] as String
    override var title: String by mutableStateOf(wrapped[Component::title.name] as String)
    override val identifier: String get() = wrapped[Component::identifier.name] as String
    override val type: Component.Type get() = wrapped.type()
    override val disabled: Boolean get() = wrapped[Component::disabled.name] as? Boolean == true
    override val depth: Int get() = wrapped[Component::depth.name] as Int
}