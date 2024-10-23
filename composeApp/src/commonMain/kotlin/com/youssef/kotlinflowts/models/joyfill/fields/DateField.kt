package com.youssef.kotlinflowts.models.joyfill.fields

interface DateField : ValueBasedField<Long> {
    val format: String?
}