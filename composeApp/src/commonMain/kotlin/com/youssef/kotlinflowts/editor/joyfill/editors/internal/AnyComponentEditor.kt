package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.ComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal open class AnyComponentEditor<out F : Component>(
    app: App,
    override val component: F,
    onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<F>(app, component, onChange), ComponentEditor {

    override var id: String
        get() = this.component.id
        set(value) {
            TODO()
        }

    override var identifier: String
        get() = this.component.identifier
        set(value) {
            TODO()
        }

    override var title: String
        get() = this.component.title
        set(value) {
            TODO()
        }

    override val type get() = component.type
}