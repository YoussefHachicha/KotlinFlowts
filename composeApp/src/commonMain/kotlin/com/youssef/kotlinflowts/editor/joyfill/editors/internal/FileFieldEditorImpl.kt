package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.FileFieldEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.fields.FileField
import com.youssef.kotlinflowts.models.joyfill.utils.Document

@PublishedApi
internal class FileFieldEditorImpl(
    document: Document,
    override val field: FileField,
    identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractFileBasedFieldEditor(document, field, identity, onChange), FileFieldEditor