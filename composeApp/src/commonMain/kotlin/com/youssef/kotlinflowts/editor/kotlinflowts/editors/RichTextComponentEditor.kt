package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.RichTextComponent

interface RichTextComponentEditor : ComponentEditor {
    override val comp: RichTextComponent
}