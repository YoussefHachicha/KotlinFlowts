package com.youssef.kotlinflowts.editor.joyfill.column.internal

import com.youssef.kotlinflowts.editor.joyfill.LayoutCollection
import com.youssef.kotlinflowts.editor.joyfill.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.EventTrigger
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.components.ColumnComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal class ColumnCollectionImpl(
    app: App,
    override val identity: IdentityGenerator,
    component: ColumnComponent,
    override val onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<ColumnComponent>(app, component, onChange), LayoutCollection {

    override fun all(): List<ComponentEditor> = component.value.map { it.toEditor() }

    override fun find(key: String): ComponentEditor? {
        val comp = component.value.find { it.id == key || it.title == key } ?: return null
        return comp.toEditor()
    }
}

