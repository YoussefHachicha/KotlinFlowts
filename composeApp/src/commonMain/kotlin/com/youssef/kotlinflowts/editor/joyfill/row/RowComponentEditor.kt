package com.youssef.kotlinflowts.editor.joyfill.row

import com.youssef.kotlinflowts.editor.joyfill.LayoutCollection
import com.youssef.kotlinflowts.editor.joyfill.editors.ListBasedComponentEditor
import com.youssef.kotlinflowts.models.joyfill.components.core.Component


interface RowComponentEditor : ListBasedComponentEditor<Component> {
    val rowComponents: LayoutCollection
}