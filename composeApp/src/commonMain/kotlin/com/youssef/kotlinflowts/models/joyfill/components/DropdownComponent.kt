package com.youssef.kotlinflowts.models.joyfill.components

import com.youssef.kotlinflowts.models.joyfill.components.core.ValueBasedComponent
import com.youssef.kotlinflowts.models.joyfill.utils.Option2

interface DropdownComponent : ValueBasedComponent<String> {
    val options: List<Option2>
}