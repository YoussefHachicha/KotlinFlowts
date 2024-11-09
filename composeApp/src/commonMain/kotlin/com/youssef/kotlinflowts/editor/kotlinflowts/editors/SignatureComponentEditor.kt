package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.SignatureComponent

interface SignatureComponentEditor : ValueBasedComponentEditor<String> {
    override val comp: SignatureComponent
}