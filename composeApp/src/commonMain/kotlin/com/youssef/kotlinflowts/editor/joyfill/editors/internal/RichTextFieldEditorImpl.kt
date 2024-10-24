package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.RichTextFieldEditor
import com.youssef.kotlinflowts.models.joyfill.fields.RichTextField
import com.youssef.kotlinflowts.models.joyfill.utils.Document

@PublishedApi
internal open class RichTextFieldEditorImpl(
    document: Document,
    override val field: RichTextField,
) : AnyFieldEditor<RichTextField>(document,field,null), RichTextFieldEditor