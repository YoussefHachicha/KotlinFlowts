package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

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