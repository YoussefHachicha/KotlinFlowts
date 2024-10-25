package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.ValueBasedComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.components.core.ValueBasedComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal abstract class AbstractValueBasedComponentEditor<V, F : ValueBasedComponent<V>>(
    app: App,
    override val component: F,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyComponentEditor<F>(app, component, onChange), ValueBasedComponentEditor<V> {
    override var value: V?
        get() = this.component.value
        set(value) {
            this.component.value = value
            notifyChange(value)
        }
}