package com.youssef.kotlinflowts.models.kotlinflowts.components

import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ValueBasedComponent

interface DateFieldComponent : ValueBasedComponent<Long> {
    val format: String?
}