package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.components.FileComponent

interface FileComponentEditor : FileBasedComponentEditor {
    override val component: FileComponent
}