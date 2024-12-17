package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.NumberFieldComponent

interface NumberFieldComponentEditor : ValueBasedComponentEditor<Double> {
    override val comp: NumberFieldComponent
}