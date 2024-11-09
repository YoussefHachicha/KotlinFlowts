package com.youssef.kotlinflowts.editor.kotlinflowts.column

import com.youssef.kotlinflowts.editor.kotlinflowts.LayoutCollection
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ListBasedComponentEditor
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component

interface ColumnComponentEditor : ListBasedComponentEditor<Component> {
    val columnComponents: LayoutCollection
}