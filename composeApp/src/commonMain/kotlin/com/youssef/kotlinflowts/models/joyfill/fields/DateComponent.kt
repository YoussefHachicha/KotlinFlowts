package com.youssef.kotlinflowts.models.joyfill.fields

interface DateComponent : ValueBasedComponent<Long> {
    val format: String?
}