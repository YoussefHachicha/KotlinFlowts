package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.ImageFieldEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.fields.ImageField
import com.youssef.kotlinflowts.models.joyfill.utils.Document

@PublishedApi
internal class ImageFieldEditorImpl(
    document: Document,
    override val field: ImageField,
    identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractFileBasedFieldEditor(document, field, identity, onChange), ImageFieldEditor