package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.DropdownComponent

interface DropdownComponentEditor : ComponentEditor, DropdownEditor {
    override val comp: DropdownComponent
}