package com.youssef.kotlinflowts.models.joyfill.components

import com.youssef.kotlinflowts.models.joyfill.components.core.ListBasedComponent
import com.youssef.kotlinflowts.models.joyfill.utils.Option2

interface MultiSelectComponent : ListBasedComponent<String> {
    val options: List<Option2>
}