package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.SignatureFieldEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.SignatureField
import com.youssef.kotlinflowts.models.joyfill.utils.Document

@PublishedApi
internal class SignatureFieldEditorImpl(
    document: Document,
    override val field: SignatureField,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedFieldEditor<String, SignatureField>(document, field, onChange), SignatureFieldEditor