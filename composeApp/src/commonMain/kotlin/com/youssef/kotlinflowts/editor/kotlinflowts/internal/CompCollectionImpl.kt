package com.youssef.kotlinflowts.editor.kotlinflowts.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.collections.CompCollection
import com.youssef.kotlinflowts.editor.kotlinflowts.column.internal.ColumnComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.ChartComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.DateComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.DropdownComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.FileComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.ImageComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.MultiSelectComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.NumberComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.SignatureComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.TableComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.TextAreaComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.TextComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.row.internal.RowComponentEditorImpl
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.MutableApp
import com.youssef.kotlinflowts.models.kotlinflowts.Screen
import com.youssef.kotlinflowts.models.kotlinflowts.components.ChartComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.ColumnComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.DateComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.DropdownComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.FileComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.ImageComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.MultiSelectComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.NumberComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.RowComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.SignatureComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextAreaComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class CompCollectionImpl(
    override val app: MutableApp,
    override val identity: IdentityGenerator,
    override val onChange: ((ChangeEvent) -> Unit)?
) : CompCollection {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun all() = app.components.map { it.toEditor() }

    private val _all: MutableStateFlow<List<ComponentEditor>> = MutableStateFlow(all())
    override val all: StateFlow<List<ComponentEditor>> = _all

    val componentsEditor: MutableList<ComponentEditor> =  all().toMutableList()

    init {
        scope.launch {
            app.builders["mainBuilder"]?.components?.collect {
                synchronizeComponents(it)
            }
        }
    }

    private fun synchronizeComponents(newComponents: List<Component>) {
        val existingEditorsMap = componentsEditor.associateBy { it.id }

        val updatedComponents = mutableListOf<ComponentEditor>()

        newComponents.forEach { component ->
            val existingEditor = existingEditorsMap[component.id]

            if (existingEditor != null) {
                existingEditor.updateFromComponent(component)
                updatedComponents.add(existingEditor)
            } else {
                updatedComponents.add(component.toEditor())
            }
        }

        componentsEditor.clear()
        componentsEditor.addAll(updatedComponents)
        _all.value = updatedComponents
    }

    private fun ComponentEditor.updateFromComponent(component: Component) {
        this.title = component.title
    }

    override fun from(screen: Screen): List<ComponentEditor> {
        val positions = screen.positions
        val ids = positions.map { it.componentId }
        return componentsEditor.filter {
            it.comp.depth == 1 &&
            it.comp.id in ids
        }.sortedBy { df ->
            positions.first { it.componentId == df.comp.id }.y
        }
    }

    override fun layoutsFrom(screen: Screen?): List<ComponentEditor> {
        if (screen == null) return emptyList()
        val positions = screen.positions
        val ids = positions.map { it.componentId }
        val filteredComponents = componentsEditor.filter { component ->
            val hasId = component.id in ids
            val isLayoutType = component.type == Component.Type.column || component.type == Component.Type.row
            hasId && isLayoutType
        }
        return filteredComponents
            .mapNotNull { component ->
                positions.find { it.componentId == component.id }?.let { position ->
                    component to position
                }
            }
            .sortedBy { (_, position) -> position.y }
            .map { it.first }
    }

    override fun from(screen: String): List<ComponentEditor> {
        val files = app.files
        val screens =
            files.flatMap { it.views }.flatMap { it.screens } + files.flatMap { it.screens }
        val s = screens.find {
            it.id == screen || it.name == screen
        } ?: return emptyList()
        return from(s)
    }


    private fun <C : Component> look(key: String, type: Component.Type): C? = app.components.find {
        (it.identifier == key || it.id == key || it.title == key) && it.type == type
    } as? C

    override fun find(key: String): ComponentEditor? {
        val component =
            all.value.find { it.identifier == key || it.id == key || it.title == key }
                ?: return null
        return component
    }

    private fun <F : Component, E : ComponentEditor> buildEditor(
        key: String,
        type: Component.Type,
        builder: (field: F) -> E?
    ): E? {
        return builder(look(key, type) ?: return null)
    }

    override fun text(key: String) =
        buildEditor(key, Component.Type.text) { component: TextComponent ->
            TextComponentEditorImpl(app, component, onChange)
        }

    override fun textarea(key: String) =
        buildEditor(key, Component.Type.textarea) { component: TextAreaComponent ->
            TextAreaComponentEditorImpl(app, component, onChange)
        }

    override fun signature(key: String) =
        buildEditor(key, Component.Type.signature) { component: SignatureComponent ->
            SignatureComponentEditorImpl(app, component, onChange)
        }

    override fun date(key: String) =
        buildEditor(key, Component.Type.date) { component: DateComponent ->
            DateComponentEditorImpl(app, component, onChange)
        }

    override fun number(key: String) =
        buildEditor(key, Component.Type.number) { component: NumberComponent ->
            NumberComponentEditorImpl(app, component, onChange)
        }

    override fun dropdown(key: String) =
        buildEditor(key, Component.Type.dropdown) { component: DropdownComponent ->
            DropdownComponentEditorImpl(app, component, identity, onChange)
        }

    override fun select(key: String) =
        buildEditor(key, Component.Type.multiSelect) { component: MultiSelectComponent ->
            MultiSelectComponentEditorImpl(app, component, identity, onChange)
        }

    override fun image(key: String) =
        buildEditor(key, Component.Type.image) { component: ImageComponent ->
            ImageComponentEditorImpl(app, component, identity, onChange)
        }

    override fun file(key: String) =
        buildEditor(key, Component.Type.file) { component: FileComponent ->
            FileComponentEditorImpl(app, component, identity, onChange)
        }

    override fun table(key: String) =
        buildEditor(key, Component.Type.table) { component: TableComponent ->
            TableComponentEditorImpl(app, component, identity, onChange)
        }

    override fun chart(key: String) =
        buildEditor(key, Component.Type.chart) { component: ChartComponent ->
            ChartComponentEditorImpl(app, component, identity, onChange)
        }

    override fun column(key: String) =
        buildEditor(key, Component.Type.column) { component: ColumnComponent ->
            ColumnComponentEditorImpl(app, component, identity, onChange)
        }


    override fun row(key: String) =
        buildEditor(key, Component.Type.row) { component: RowComponent ->
            RowComponentEditorImpl(app, component, identity, onChange)
        }
}
