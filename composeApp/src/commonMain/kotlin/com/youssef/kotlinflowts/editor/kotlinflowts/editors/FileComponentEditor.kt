package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.FileComponent

interface FileComponentEditor : FileBasedComponentEditor {
    override val comp: FileComponent
}