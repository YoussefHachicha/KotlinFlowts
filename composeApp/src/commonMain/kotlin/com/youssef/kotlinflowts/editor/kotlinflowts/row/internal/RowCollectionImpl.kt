package com.youssef.kotlinflowts.editor.kotlinflowts.row.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.LayoutCollection
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.EventTrigger
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.RowComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class RowCollectionImpl(
    app: App,
    override val identity: IdentityGenerator,
    component: RowComponent,
    override val onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<RowComponent>(app, component, onChange), LayoutCollection {

    override val all = app.builders[component.id]?.components.orEmpty()

    override fun getAllComponents(): List<ComponentEditor> = app.builders[component.id]?.components.orEmpty()

    override fun find(key: String): ComponentEditor? {
        val comp = component.value.find { it.id == key || it.title == key } ?: return null
        return comp.toEditor()
    }
}