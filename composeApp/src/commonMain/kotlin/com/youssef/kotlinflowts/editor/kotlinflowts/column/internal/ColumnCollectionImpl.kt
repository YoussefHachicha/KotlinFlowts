package com.youssef.kotlinflowts.editor.kotlinflowts.column.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.LayoutCollection
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.EventTrigger
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.ColumnComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

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

