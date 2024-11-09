package com.youssef.kotlinflowts.editor.joyfill

import com.youssef.kotlinflowts.editor.joyfill.column.internal.ColumnComponentEditorImpl
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
import com.youssef.kotlinflowts.editor.joyfill.row.internal.RowComponentEditorImpl
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
import com.youssef.kotlinflowts.models.joyfill.components.RowComponent
import com.youssef.kotlinflowts.models.joyfill.components.SignatureComponent
import com.youssef.kotlinflowts.models.joyfill.components.TableComponent
import com.youssef.kotlinflowts.models.joyfill.components.TextAreaComponent
import com.youssef.kotlinflowts.models.joyfill.components.TextComponent
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.utils.App

interface LayoutCollection {
    val app: App
    val onChange: ((ChangeEvent) -> Unit)?
    val identity: IdentityGenerator

    fun Component.toEditor(): ComponentEditor = when (this) {
        is TextComponent                -> TextComponentEditorImpl(app, this, onChange)
        is TextAreaComponent            -> TextAreaComponentEditorImpl(app, this, onChange)
        is NumberComponent              -> NumberComponentEditorImpl(app, this, onChange)
        is DropdownComponent            -> DropdownComponentEditorImpl(app, this, onChange)
        is MultiSelectComponent         -> MultiSelectComponentEditorImpl(app, this, onChange)
        is DateComponent                -> DateComponentEditorImpl(app, this, onChange)
        is SignatureComponent           -> SignatureComponentEditorImpl(app, this, onChange)
        is TableComponent               -> TableComponentEditorImpl(app, this, identity, onChange)
        is ChartComponent               -> ChartComponentEditorImpl(app, this, identity, onChange)
        is ImageComponent               -> ImageComponentEditorImpl(app, this, identity, onChange)
        is FileComponent                -> FileComponentEditorImpl(app, this, identity, onChange)
        is RichTextComponent            -> RichTextComponentEditorImpl(app, this)
        is BlockComponent               -> BlockComponentEditorImpl(app, this)
        is ColumnComponent              -> ColumnComponentEditorImpl(app, this, identity, onChange)
        is RowComponent                 -> RowComponentEditorImpl(app, this, identity, onChange)
        else /*  is UnknownComponent */ -> AnyComponentEditor(app, this, onChange)
    }

    fun all(): List<ComponentEditor>

    fun find(key: String): ComponentEditor?
}