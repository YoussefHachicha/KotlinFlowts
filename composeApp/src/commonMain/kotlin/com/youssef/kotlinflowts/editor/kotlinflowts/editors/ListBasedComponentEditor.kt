package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ListBasedComponent

interface ListBasedComponentEditor<V> : ComponentEditor {
    override val comp: ListBasedComponent<V>
    val value: MutableList<V>
}