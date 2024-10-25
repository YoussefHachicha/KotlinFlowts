package com.youssef.kotlinflowts.editor.joyfill.internal

import com.youssef.kotlinflowts.editor.joyfill.collections.CompCollection
import com.youssef.kotlinflowts.editor.joyfill.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.AnyComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.BlockComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.ChartComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.DateComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.DropdownComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.FileComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.ImageComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.MultiSelectComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.NumberComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.RichTextComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.SignatureComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.TableComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.TextAreaComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.TextComponentEditorImpl
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.MutableApp
import com.youssef.kotlinflowts.models.joyfill.Screen
import com.youssef.kotlinflowts.models.joyfill.fields.BlockComponent
import com.youssef.kotlinflowts.models.joyfill.fields.ChartComponent
import com.youssef.kotlinflowts.models.joyfill.fields.DateComponent
import com.youssef.kotlinflowts.models.joyfill.fields.DropdownComponent
import com.youssef.kotlinflowts.models.joyfill.fields.Component
import com.youssef.kotlinflowts.models.joyfill.fields.FileComponent
import com.youssef.kotlinflowts.models.joyfill.fields.ImageComponent
import com.youssef.kotlinflowts.models.joyfill.fields.MultiSelectComponent
import com.youssef.kotlinflowts.models.joyfill.fields.NumberComponent
import com.youssef.kotlinflowts.models.joyfill.fields.RichTextComponent
import com.youssef.kotlinflowts.models.joyfill.fields.SignatureComponent
import com.youssef.kotlinflowts.models.joyfill.fields.TableComponent
import com.youssef.kotlinflowts.models.joyfill.fields.TextAreaComponent
import com.youssef.kotlinflowts.models.joyfill.fields.TextComponent

internal class CompCollectionImpl(
    private val app: MutableApp,
    private val identity: IdentityGenerator,
    private val onChange: ((ChangeEvent) -> Unit)?
) : CompCollection {
    override fun all() = app.components.map { it.toEditor() }

    override fun from(screen: String): List<ComponentEditor> {
        val files = app.files
        val screens = files.flatMap { it.views }.flatMap { it.screens } + files.flatMap { it.screens }
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
        val component = app.components.find { it.identifier == key || it.id == key || it.title == key } ?: return null
        return component.toEditor()
    }

    private fun Component.toEditor(): ComponentEditor = when (this) {
        is TextComponent -> TextComponentEditorImpl(app, this, onChange)
        is TextAreaComponent -> TextAreaComponentEditorImpl(app, this, onChange)
        is NumberComponent -> NumberComponentEditorImpl(app, this, onChange)
        is DropdownComponent -> DropdownComponentEditorImpl(app, this, onChange)
        is MultiSelectComponent -> MultiSelectComponentEditorImpl(app, this, onChange)
        is DateComponent -> DateComponentEditorImpl(app, this, onChange)
        is SignatureComponent -> SignatureComponentEditorImpl(app, this, onChange)
        is TableComponent -> TableComponentEditorImpl(app, this, identity, onChange)
        is ChartComponent -> ChartComponentEditorImpl(app, this, identity, onChange)
        is ImageComponent -> ImageComponentEditorImpl(app, this, identity, onChange)
        is FileComponent -> FileComponentEditorImpl(app, this, identity, onChange)
        is RichTextComponent -> RichTextComponentEditorImpl(app, this)
        is BlockComponent -> BlockComponentEditorImpl(app, this)
        else /*  is UnknownComponent */ -> AnyComponentEditor(app, this, onChange)
    }

    private fun <F : Component, E : ComponentEditor> buildEditor(key: String, type: Component.Type, builder: (field: F) -> E?): E? {
        return builder(look(key, type) ?: return null)
    }

    override fun text(key: String) = buildEditor(key, Component.Type.text) { component: TextComponent ->
        TextComponentEditorImpl(app, component, onChange)
    }

    override fun textarea(key: String) = buildEditor(key, Component.Type.textarea) { component: TextAreaComponent ->
        TextAreaComponentEditorImpl(app, component, onChange)
    }

    override fun signature(key: String) = buildEditor(key, Component.Type.signature) { component: SignatureComponent ->
        SignatureComponentEditorImpl(app, component, onChange)
    }

    override fun date(key: String) = buildEditor(key, Component.Type.date) { component: DateComponent ->
        DateComponentEditorImpl(app, component, onChange)
    }

    override fun number(key: String) = buildEditor(key, Component.Type.number) { component: NumberComponent ->
        NumberComponentEditorImpl(app, component, onChange)
    }

    override fun dropdown(key: String) = buildEditor(key, Component.Type.dropdown) { component: DropdownComponent ->
        DropdownComponentEditorImpl(app, component, onChange)
    }

    override fun select(key: String) = buildEditor(key, Component.Type.multiSelect) { component: MultiSelectComponent ->
        MultiSelectComponentEditorImpl(app, component, onChange)
    }

    override fun image(key: String) = buildEditor(key, Component.Type.image) { component: ImageComponent ->
        ImageComponentEditorImpl(app, component, identity, onChange)
    }

    override fun file(key: String) = buildEditor(key, Component.Type.file) { component: FileComponent ->
        FileComponentEditorImpl(app, component, identity, onChange)
    }

    override fun table(key: String) = buildEditor(key, Component.Type.table) { component: TableComponent ->
        TableComponentEditorImpl(app, component, identity, onChange)
    }

    override fun chart(key: String) = buildEditor(key, Component.Type.chart) { component: ChartComponent ->
        ChartComponentEditorImpl(app, component, identity, onChange)
    }
}