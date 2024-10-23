package com.youssef.kotlinflowts.models.joyfill.fields

import com.youssef.kotlinflowts.models.joyfill.utils.Option2

interface DropdownField : ValueBasedField<String> {
    val options: List<Option2>
}