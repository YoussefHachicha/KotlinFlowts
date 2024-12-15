package com.youssef.kotlinflowts.models.kotlinflowts.components

import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ListBasedComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2

interface MultiSelectComponent : ListBasedComponent<String> {
    val options: MutableList<Option2>
    val multiple: Boolean
}