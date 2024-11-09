package com.youssef.kotlinflowts.models.kotlinflowts.components.core

interface ListBasedComponent<V> : Component {
    val value: MutableList<V>
}