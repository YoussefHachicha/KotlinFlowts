package com.youssef.kotlinflowts.editor.kotlinflowts.row.internal

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import com.youssef.kotlinflowts.editor.kotlinflowts.LayoutCollection
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.EventTrigger
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.RowComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@PublishedApi
internal class RowCollectionImpl(
    app: App,
    override val identity: IdentityGenerator,
    component: RowComponent,
    override val onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<RowComponent>(app, component, onChange), LayoutCollection {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

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