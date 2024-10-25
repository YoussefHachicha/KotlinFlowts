package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.components.DropdownComponent

interface DropdownComponentEditor : ComponentEditor, DropdownEditor {
    override val component: DropdownComponent
}