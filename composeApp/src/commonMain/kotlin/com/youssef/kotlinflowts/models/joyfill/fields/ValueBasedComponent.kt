package com.youssef.kotlinflowts.models.joyfill.fields

interface ValueBasedComponent<V> : Component {
    var value: V?
}