package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.FileComponent

interface FileComponentEditor : FileBasedComponentEditor {
    override val component: FileComponent
}