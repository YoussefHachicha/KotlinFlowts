package com.youssef.kotlinflowts.editor.kotlinflowts

import com.youssef.kotlinflowts.editor.kotlinflowts.column.internal.ColumnComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.AnyComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.BlockComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.ChartComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.DateFieldFieldComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.DropdownComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.FileComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.ImageComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.MultiSelectComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.NumberFieldComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.RichTextComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.SignatureComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.TableComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.TextComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.TextFieldAreaComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.TextFieldComponentEditorImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.row.internal.RowComponentEditorImpl
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.BlockComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.ChartComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.ColumnComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.DateFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.DropdownComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.FileComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.ImageComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.MultiSelectComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.NumberFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.RichTextComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.RowComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.SignatureComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextFieldAreaComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

interface LayoutCollection {
    val app: App
    val onChange: ((ChangeEvent) -> Unit)?
    val identity: IdentityGenerator

    fun Component.toEditor(): ComponentEditor = when (this) {
        is TextComponent   -> TextComponentEditorImpl(app, this, onChange)
        is TextFieldComponent     -> TextFieldComponentEditorImpl(app, this, onChange)
        is TextFieldAreaComponent -> TextFieldAreaComponentEditorImpl(app, this, onChange)
        is NumberFieldComponent   -> NumberFieldComponentEditorImpl(app, this, onChange)
        is DropdownComponent      -> DropdownComponentEditorImpl(app, this, identity, onChange)
        is MultiSelectComponent -> MultiSelectComponentEditorImpl(app, this, identity, onChange)
        is DateFieldComponent   -> DateFieldFieldComponentEditorImpl(app, this, onChange)
        is SignatureComponent   -> SignatureComponentEditorImpl(app, this, onChange)
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

    fun getAllComponents(): List<ComponentEditor>

    fun find(key: String): ComponentEditor?

    val all: List<ComponentEditor>

}