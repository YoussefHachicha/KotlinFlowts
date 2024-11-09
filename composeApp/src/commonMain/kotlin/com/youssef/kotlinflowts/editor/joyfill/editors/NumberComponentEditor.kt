package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.components.NumberComponent

interface NumberComponentEditor : ValueBasedComponentEditor<Double> {
    override val comp: NumberComponent
}