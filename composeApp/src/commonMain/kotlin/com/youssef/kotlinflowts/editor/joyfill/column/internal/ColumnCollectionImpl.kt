package com.youssef.kotlinflowts.editor.joyfill.column.internal

import com.youssef.kotlinflowts.editor.joyfill.chart.LineEditor
import com.youssef.kotlinflowts.editor.joyfill.chart.internal.LineEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.collections.CompCollection
import com.youssef.kotlinflowts.editor.joyfill.column.ColumnCollection
import com.youssef.kotlinflowts.editor.joyfill.column.ColumnComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ChartComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.DateComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.DropdownComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.FileComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ImageComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.MultiSelectComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.NumberComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.SignatureComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TableComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TextAreaComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TextComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.AnyComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.BlockComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.ChartComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.DateComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.DropdownComponentEditorImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.EventTrigger
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
import com.youssef.kotlinflowts.models.joyfill.components.BlockComponent
import com.youssef.kotlinflowts.models.joyfill.components.ChartComponent
import com.youssef.kotlinflowts.models.joyfill.components.ColumnComponent
import com.youssef.kotlinflowts.models.joyfill.components.DateComponent
import com.youssef.kotlinflowts.models.joyfill.components.DropdownComponent
import com.youssef.kotlinflowts.models.joyfill.components.FileComponent
import com.youssef.kotlinflowts.models.joyfill.components.ImageComponent
import com.youssef.kotlinflowts.models.joyfill.components.MultiSelectComponent
import com.youssef.kotlinflowts.models.joyfill.components.NumberComponent
import com.youssef.kotlinflowts.models.joyfill.components.RichTextComponent
import com.youssef.kotlinflowts.models.joyfill.components.SignatureComponent
import com.youssef.kotlinflowts.models.joyfill.components.TableComponent
import com.youssef.kotlinflowts.models.joyfill.components.TextAreaComponent
import com.youssef.kotlinflowts.models.joyfill.components.TextComponent
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal class ColumnCollectionImpl(
    app: App,
    val identity: IdentityGenerator,
    field: ColumnComponent,
    val onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<ColumnComponent>(app, field, onChange), ColumnCollection {

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
        is ColumnComponent -> ColumnComponentEditorImpl(app, this, identity, onChange)
        else /*  is UnknownComponent */ -> AnyComponentEditor(app, this, onChange)
    }

    override fun all(): List<ComponentEditor> = field.value.map { it.toEditor() }

    override fun find(key: String): ComponentEditor? {
        val comp = field.value.find { it.id == key || it.title == key } ?: return null
        return comp.toEditor()
    }
}