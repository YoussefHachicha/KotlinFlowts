package com.youssef.kotlinflowts.models.kotlinflowts.components

import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ValueBasedComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2

interface DropdownComponent : ValueBasedComponent<String> {
    val options: List<Option2>
}