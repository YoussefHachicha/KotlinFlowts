package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.components.SignatureComponent

interface SignatureComponentEditor : ValueBasedComponentEditor<String> {
    override val component: SignatureComponent
}