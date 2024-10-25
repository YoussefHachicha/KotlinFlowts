package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.components.DateComponent

interface DateComponentEditor : ValueBasedComponentEditor<Long> {
    override val component: DateComponent
}