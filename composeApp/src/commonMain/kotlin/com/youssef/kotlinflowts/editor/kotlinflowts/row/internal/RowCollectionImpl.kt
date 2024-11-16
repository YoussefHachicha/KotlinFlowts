package com.youssef.kotlinflowts.editor.kotlinflowts.row.internal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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

    override fun all(): List<ComponentEditor> = component.value.map { it.toEditor() }

    override val all: List<ComponentEditor> by mutableStateOf(component.value.map { it.toEditor() })

    override fun find(key: String): ComponentEditor? {
        val comp = component.value.find { it.id == key || it.title == key } ?: return null
        return comp.toEditor()
    }
}