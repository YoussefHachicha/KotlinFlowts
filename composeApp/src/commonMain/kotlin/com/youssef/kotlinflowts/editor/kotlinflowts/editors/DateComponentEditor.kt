package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.DateComponent

interface DateComponentEditor : ValueBasedComponentEditor<Long> {
    override val comp: DateComponent
}