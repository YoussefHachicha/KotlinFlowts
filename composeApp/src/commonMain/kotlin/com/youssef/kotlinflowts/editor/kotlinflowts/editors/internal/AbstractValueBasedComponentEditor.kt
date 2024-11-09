package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ValueBasedComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ValueBasedComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal abstract class AbstractValueBasedComponentEditor<V, F : ValueBasedComponent<V>>(
    app: App,
    override val comp: F,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyComponentEditor<F>(app, comp, onChange), ValueBasedComponentEditor<V> {
    override var value: V?
        get() = this.comp.value
        set(value) {
            this.comp.value = value
            notifyChange(value)
        }
}