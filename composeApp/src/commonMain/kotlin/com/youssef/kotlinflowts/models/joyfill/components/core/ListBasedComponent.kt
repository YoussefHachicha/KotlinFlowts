package com.youssef.kotlinflowts.models.joyfill.components.core

interface ListBasedComponent<V> : Component {
    val value: MutableList<V>
}