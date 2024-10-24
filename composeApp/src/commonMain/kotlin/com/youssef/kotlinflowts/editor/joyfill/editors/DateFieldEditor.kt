package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.DateField

interface DateFieldEditor : ValueBasedFieldEditor<Long> {
    override val field: DateField
}