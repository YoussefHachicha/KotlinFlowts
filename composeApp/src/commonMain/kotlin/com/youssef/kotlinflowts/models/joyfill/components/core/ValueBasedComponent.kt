package com.youssef.kotlinflowts.models.joyfill.components.core

interface ValueBasedComponent<V> : Component {
    var value: V?
}