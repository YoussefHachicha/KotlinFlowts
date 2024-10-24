package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.SignatureField

interface SignatureFieldEditor : ValueBasedFieldEditor<String> {
    override val field: SignatureField
}