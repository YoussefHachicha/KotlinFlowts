package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.SignatureComponent

interface SignatureComponentEditor : ValueBasedComponentEditor<String> {
    override val component: SignatureComponent
}