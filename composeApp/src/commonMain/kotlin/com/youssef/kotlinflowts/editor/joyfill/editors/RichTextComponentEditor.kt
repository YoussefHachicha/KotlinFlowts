package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.components.RichTextComponent

interface RichTextComponentEditor : ComponentEditor {
    override val component: RichTextComponent
}