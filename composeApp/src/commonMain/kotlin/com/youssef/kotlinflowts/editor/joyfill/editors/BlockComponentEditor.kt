package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.components.BlockComponent


interface BlockComponentEditor : ComponentEditor {
    override val component: BlockComponent
}