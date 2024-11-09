package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.BlockComponent


interface BlockComponentEditor : ComponentEditor {
    override val comp: BlockComponent
}