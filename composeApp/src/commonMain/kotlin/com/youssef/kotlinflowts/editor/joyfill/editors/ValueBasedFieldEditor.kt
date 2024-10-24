package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.ValueBasedField


interface ValueBasedFieldEditor<V> : FieldEditor {
    override val field: ValueBasedField<V>
    var value: V?
}