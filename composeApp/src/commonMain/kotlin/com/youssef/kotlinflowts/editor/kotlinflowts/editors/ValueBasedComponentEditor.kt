package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import androidx.compose.ui.graphics.Color
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ValueBasedComponent


interface ValueBasedComponentEditor<V> : ComponentEditor {
    override val comp: ValueBasedComponent<V>
    var value: V?
}