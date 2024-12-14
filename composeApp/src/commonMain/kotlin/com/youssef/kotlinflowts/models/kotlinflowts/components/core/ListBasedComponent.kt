package com.youssef.kotlinflowts.models.kotlinflowts.components.core

import androidx.compose.runtime.mutableStateListOf

interface ListBasedComponent<V> : Component {
    val value: MutableList<V>
}

