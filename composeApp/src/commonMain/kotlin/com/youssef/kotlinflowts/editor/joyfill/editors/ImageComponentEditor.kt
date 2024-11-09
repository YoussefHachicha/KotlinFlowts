package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.components.ImageComponent

interface ImageComponentEditor : FileBasedComponentEditor {
    override val comp: ImageComponent
}