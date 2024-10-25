package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.RichTextComponent

interface RichTextComponentEditor : ComponentEditor {
    override val component: RichTextComponent
}