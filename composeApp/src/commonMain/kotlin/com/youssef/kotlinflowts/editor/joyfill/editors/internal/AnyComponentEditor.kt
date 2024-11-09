package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.ComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal open class AnyComponentEditor<out F : Component>(
    app: App,
    override val comp: F,
    onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<F>(app, comp, onChange), ComponentEditor {

    override var id: String
        get() = this.comp.id
        set(value) {
            TODO()
        }

    override var identifier: String
        get() = this.comp.identifier
        set(value) {
            TODO()
        }

    override var title: String
        get() = this.comp.title
        set(value) {
            TODO()
        }

    override val type get() = comp.type
}