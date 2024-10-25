package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.ValueBasedComponent


interface ValueBasedComponentEditor<V> : ComponentEditor {
    override val component: ValueBasedComponent<V>
    var value: V?
}