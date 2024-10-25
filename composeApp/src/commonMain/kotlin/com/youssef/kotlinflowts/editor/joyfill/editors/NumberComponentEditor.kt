package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.NumberComponent

interface NumberComponentEditor : ValueBasedComponentEditor<Double> {
    override val component: NumberComponent
}