package com.youssef.kotlinflowts.models.joyfill.fields

interface ValueBasedField<V> : Field {
    var value: V?
}