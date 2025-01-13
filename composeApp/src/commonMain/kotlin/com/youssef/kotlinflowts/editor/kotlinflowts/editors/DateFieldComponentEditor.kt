package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.DateFieldComponent

interface DateFieldComponentEditor : ValueBasedComponentEditor<Long> {
    override val comp: DateFieldComponent
}