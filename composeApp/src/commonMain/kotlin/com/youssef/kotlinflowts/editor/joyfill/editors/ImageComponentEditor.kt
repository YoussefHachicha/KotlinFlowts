package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.ImageComponent

interface ImageComponentEditor : FileBasedComponentEditor {
    override val component: ImageComponent
}