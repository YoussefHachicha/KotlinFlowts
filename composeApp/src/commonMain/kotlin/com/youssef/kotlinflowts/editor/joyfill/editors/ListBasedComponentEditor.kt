package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.ListBasedComponent

interface ListBasedComponentEditor<V> : ComponentEditor {
    override val component: ListBasedComponent<V>
    val value: MutableList<V>
}