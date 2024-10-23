package com.youssef.kotlinflowts.models.joyfill.fields

interface ListBasedField<V> : Field {
    val value: MutableList<V>
}