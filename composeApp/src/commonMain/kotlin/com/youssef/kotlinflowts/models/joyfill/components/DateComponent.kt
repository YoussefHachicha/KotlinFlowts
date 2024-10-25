package com.youssef.kotlinflowts.models.joyfill.components

import com.youssef.kotlinflowts.models.joyfill.components.core.ValueBasedComponent

interface DateComponent : ValueBasedComponent<Long> {
    val format: String?
}