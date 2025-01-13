package com.youssef.kotlinflowts.editor.kotlinflowts.column.internal

import androidx.compose.runtime.mutableStateListOf
import com.youssef.kotlinflowts.editor.kotlinflowts.LayoutCollection
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.EventTrigger
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.ColumnComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@PublishedApi
internal class ColumnCollectionImpl(
    app: App,
    override val identity: IdentityGenerator,
    component: ColumnComponent,
    override val onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<ColumnComponent>(app, component, onChange), LayoutCollection {

    override val all = app.builders[component.id]?.components.orEmpty()

    override fun getAll(): List<ComponentEditor> = app.builders[component.id]?.components.orEmpty()

    override fun find(key: String): ComponentEditor? {
        val comp = component.value.find { it.id == key || it.title == key } ?: return null
        return comp.toEditor()
    }
}

