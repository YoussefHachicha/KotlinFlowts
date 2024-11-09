package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.components.core.ListBasedComponent

interface ListBasedComponentEditor<V> : ComponentEditor {
    override val comp: ListBasedComponent<V>
    val value: MutableList<V>
}