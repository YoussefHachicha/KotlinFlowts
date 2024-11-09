package com.youssef.kotlinflowts.editor.kotlinflowts.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.collections.CompCollection
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.ChartComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.column.internal.ColumnComponentEditorImpl
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
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.components.FileComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.ImageComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.MultiSelectComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.NumberComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.RowComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.SignatureComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextAreaComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextComponent

internal class CompCollectionImpl(
    override val app: MutableApp,
    override val identity: IdentityGenerator,
    override val onChange: ((ChangeEvent) -> Unit)?
) : CompCollection {
    override fun all() = app.components.map { it.toEditor() }

    override fun from(screen: String): List<ComponentEditor> {
        val files = app.files
        val screens =
            files.flatMap { it.views }.flatMap { it.screens } + files.flatMap { it.screens }
        val s = screens.find {
            it.id == screen || it.name == screen
        } ?: return emptyList()
        return from(s)
    }

    override fun from(screen: Screen): List<ComponentEditor> {
        val positions = screen.positions
        val ids = positions.map { it.componentId }
        return app.components.filter {
            it.id in ids
        }.sortedBy { df ->
            positions.first { it.componentId == df.id }.y
        }.map { it.toEditor() }
    }

    private fun <C : Component> look(key: String, type: Component.Type): C? = app.components.find {
        (it.identifier == key || it.id == key || it.title == key) && it.type == type
    } as? C

    override fun find(key: String): ComponentEditor? {
        val component =
            app.components.find { it.identifier == key || it.id == key || it.title == key }
                ?: return null
        return component.toEditor()
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
            DropdownComponentEditorImpl(app, component, onChange)
        }

    override fun select(key: String) =
        buildEditor(key, Component.Type.multiSelect) { component: MultiSelectComponent ->
            MultiSelectComponentEditorImpl(app, component, onChange)
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