package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.components.RichTextComponent
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.components.core.ListBasedComponent

interface ColumnComponentEditor : ListBasedComponentEditor<Component> {
    override val component: ListBasedComponent<Component>
}
