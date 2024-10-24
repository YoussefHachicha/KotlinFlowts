package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.BlockFieldEditor
import com.youssef.kotlinflowts.models.joyfill.fields.BlockField
import com.youssef.kotlinflowts.models.joyfill.utils.Document

@PublishedApi
internal open class BlockFieldEditorImpl(
    document: Document,
    override val field: BlockField,
) : AnyFieldEditor<BlockField>(document,field,null), BlockFieldEditor