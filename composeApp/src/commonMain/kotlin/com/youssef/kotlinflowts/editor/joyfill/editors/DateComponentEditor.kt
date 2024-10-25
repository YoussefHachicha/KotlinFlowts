package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.DateComponent

interface DateComponentEditor : ValueBasedComponentEditor<Long> {
    override val component: DateComponent
}