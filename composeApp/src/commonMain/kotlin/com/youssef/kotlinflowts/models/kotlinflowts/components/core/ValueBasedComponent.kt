package com.youssef.kotlinflowts.models.kotlinflowts.components.core

interface ValueBasedComponent<V> : Component {
    var value: V?
}