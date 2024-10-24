package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.NumberField

interface NumberFieldEditor : ValueBasedFieldEditor<Double> {
    override val field: NumberField
}