package com.youssef.kotlinflowts.editor.kotlinflowts.column.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.LayoutCollection
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.EventTrigger
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.ColumnComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@PublishedApi
internal class ColumnCollectionImpl(
    app: App,
    override val identity: IdentityGenerator,
    component: ColumnComponent,
    override val onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<ColumnComponent>(app, component, onChange), LayoutCollection {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    //ok so my issue here i need my builder here so that i can get my components from it
    init {
        scope.launch {
            app.builders[component.id]?.components?.collect {
                _all.value = it.map { it.toEditor() }
            }
        }
    }

    private val _all: MutableStateFlow<List<ComponentEditor>> = MutableStateFlow(emptyList())
    override val all: StateFlow<List<ComponentEditor>> = _all

    override fun all(): List<ComponentEditor> = _all.value

    override fun find(key: String): ComponentEditor? {
        val comp = component.value.find { it.id == key || it.title == key } ?: return null
        return comp.toEditor()
    }
}

