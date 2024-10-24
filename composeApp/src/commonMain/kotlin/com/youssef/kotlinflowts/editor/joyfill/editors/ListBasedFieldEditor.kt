package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.ListBasedField

interface ListBasedFieldEditor<V> : FieldEditor {
    override val field: ListBasedField<V>
    val value: MutableList<V>
}