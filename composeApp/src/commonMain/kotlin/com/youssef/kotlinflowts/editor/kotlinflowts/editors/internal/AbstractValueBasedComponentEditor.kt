package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ValueBasedComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ValueBasedComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal abstract class AbstractValueBasedComponentEditor<V, C : ValueBasedComponent<V>>(
    app: App,
    override val comp: C,
    onChange: ((ChangeEvent) -> Unit)?,
    private val initialValue:  V? = comp.value
) : AnyComponentEditor<C>(app, comp, onChange), ValueBasedComponentEditor<V> {
    override var value: V? by mutableStateOf(initialValue)

    override fun changeValue(value: V) {
        this.value = value
        notifyChange(value)
    }
}