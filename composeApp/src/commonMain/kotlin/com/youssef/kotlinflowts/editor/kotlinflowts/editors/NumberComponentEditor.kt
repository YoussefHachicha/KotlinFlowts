package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.NumberComponent

interface NumberComponentEditor : ValueBasedComponentEditor<Double> {
    override val comp: NumberComponent
}