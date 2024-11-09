package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.components.core.ValueBasedComponent


interface ValueBasedComponentEditor<V> : ComponentEditor {
    override val comp: ValueBasedComponent<V>
    var value: V?
}