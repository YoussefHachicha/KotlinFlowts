package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.models.kotlinflowts.components.AbstractComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ValueBasedComponent

@PublishedApi
internal abstract class AbstractValueBasedComponent<V>(
    wrapped: MutableMap<String, Any?>
) : AbstractComponent(wrapped), ValueBasedComponent<V> {
    override var value: V? by mutableStateOf(wrapped[ValueBasedComponent<*>::value.name] as? V)
}