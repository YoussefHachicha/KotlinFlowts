package com.youssef.kotlinflowts.models.kotlinflowts.components

import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ValueBasedComponent

interface DateComponent : ValueBasedComponent<Long> {
    val format: String?
}