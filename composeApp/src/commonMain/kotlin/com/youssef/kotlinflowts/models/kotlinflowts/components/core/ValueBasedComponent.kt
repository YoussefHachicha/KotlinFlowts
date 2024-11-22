package com.youssef.kotlinflowts.models.kotlinflowts.components.core

interface ValueBasedComponent<V> : MutableComponent {
    var value: V?
}