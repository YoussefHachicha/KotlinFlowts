package com.youssef.kotlinflowts.editor.kotlinflowts.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.collections.CompCollection
import com.youssef.kotlinflowts.editor.kotlinflowts.column.internal.ColumnComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.ChartComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.DateFieldFieldComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.DropdownComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.FileComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.ImageComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.MultiSelectComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.NumberFieldComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.SignatureComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.TableComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.TextComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.TextFieldAreaComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.TextFieldComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.row.internal.RowComponentEditorImpl
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.MutableApp
import com.youssef.kotlinflowts.models.kotlinflowts.Screen
import com.youssef.kotlinflowts.models.kotlinflowts.components.ChartComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.ColumnComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.DateFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.DropdownComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.FileComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.ImageComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.MultiSelectComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.NumberFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.RowComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.SignatureComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextFieldAreaComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextFieldComponent
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
    override fun getAll() = app.builders["mainBuilder"]?.components.orEmpty()
    override val all: List<ComponentEditor> = app.builders["mainBuilder"]?.components.orEmpty()

    override fun from(screen: Screen): List<ComponentEditor> {
        val positions = screen.positions
        val ids = positions.map { it.componentId }
        return all.filter {
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
        val filteredComponents = all.filter { component ->
            val hasId = component.id in ids
            val isLayoutType =
                component.type == Component.Type.column || component.type == Component.Type.row
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
            all.find { it.identifier == key || it.id == key || it.title == key }
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

    override fun text(key: String): TextComponentEditor? =
        buildEditor(key, Component.Type.text) { component: TextComponent ->
            TextComponentEditorImpl(app, component, onChange)
        }

    override fun textField(key: String) =
        buildEditor(key, Component.Type.textField) { component: TextFieldComponent ->
            TextFieldComponentEditorImpl(app, component, onChange)
        }

    override fun textFieldArea(key: String) =
        buildEditor(key, Component.Type.textFieldArea) { component: TextFieldAreaComponent ->
            TextFieldAreaComponentEditorImpl(app, component, onChange)
        }

    override fun signature(key: String) =
        buildEditor(key, Component.Type.signature) { component: SignatureComponent ->
            SignatureComponentEditorImpl(app, component, onChange)
        }

    override fun dateField(key: String) =
        buildEditor(key, Component.Type.dateField) { component: DateFieldComponent ->
            DateFieldFieldComponentEditorImpl(app, component, onChange)
        }

    override fun numberField(key: String) =
        buildEditor(key, Component.Type.numberField) { component: NumberFieldComponent ->
            NumberFieldComponentEditorImpl(app, component, onChange)
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
