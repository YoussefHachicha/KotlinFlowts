package com.youssef.kotlinflowts.models.joyfill.fields

import com.youssef.kotlinflowts.models.joyfill.utils.Option2

interface MultiSelectField : ListBasedField<String> {
    val options: List<Option2>
}