package com.youssef.kotlinflowts.models.joyfill.fields

interface ListBasedComponent<V> : Component {
    val value: MutableList<V>
}