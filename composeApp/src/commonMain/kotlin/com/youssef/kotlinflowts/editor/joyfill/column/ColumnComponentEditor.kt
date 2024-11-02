package com.youssef.kotlinflowts.editor.joyfill.column

import com.youssef.kotlinflowts.editor.joyfill.editors.ListBasedComponentEditor
import com.youssef.kotlinflowts.models.joyfill.components.core.Component

interface ColumnComponentEditor : ListBasedComponentEditor<Component> {
    val columnComponents:  ColumnCollection
}
