package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.ImageComponent

interface ImageComponentEditor : FileBasedComponentEditor {
    override val comp: ImageComponent
}