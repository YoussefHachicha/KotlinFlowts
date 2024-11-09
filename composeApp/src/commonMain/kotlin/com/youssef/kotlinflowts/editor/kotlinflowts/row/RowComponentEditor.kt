package com.youssef.kotlinflowts.editor.kotlinflowts.row

import com.youssef.kotlinflowts.editor.kotlinflowts.LayoutCollection
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ListBasedComponentEditor
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component


interface RowComponentEditor : ListBasedComponentEditor<Component> {
    val rowComponents: LayoutCollection
}