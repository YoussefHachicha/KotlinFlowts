package com.youssef.kotlinflowts.builder.kotlinflowts.column

import androidx.compose.runtime.mutableStateListOf
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.ComponentPosition
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.MutableApp

class ColumnBuilderImpl(
    override val identity: IdentityGenerator,
    override val app: MutableApp,
    override val depth: Int,
    override val builderId: String,
    override val onChange: ((ChangeEvent) -> Unit)?,
): ColumBuilder {
    private val _components = mutableStateListOf<ComponentEditor>()
    override val components: List<ComponentEditor> = _components

    override fun add(component: ComponentEditor, position: ComponentPosition) {
        _components.add(component)
        app.components.add(component.comp)
    }

    override fun delete(id: String) {
        val componentIndex = _components.indexOfFirst { it.id == id }
        if (componentIndex != -1) {
            _components.removeAt(componentIndex)
        }

        // Find and remove from app.components
        val appComponentIndex = app.components.indexOfFirst { it.id == id }
        if (appComponentIndex != -1) {
            app.components.removeAt(appComponentIndex)
        }
    }
}