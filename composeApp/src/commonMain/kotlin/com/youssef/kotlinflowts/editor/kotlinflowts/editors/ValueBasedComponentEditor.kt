package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ValueBasedComponent


interface ValueBasedComponentEditor<V> : ComponentEditor {
    override val comp: ValueBasedComponent<V>
    var value: V?
    fun changeValue(value: String) {
        this.value = value as V
    }
}