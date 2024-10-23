package com.youssef.kotlinflowts.models.joyfill.fields.table

import com.youssef.kotlinflowts.models.joyfill.utils.Option2

interface DropdownColumn : Column {
    val options: List<Option2>
    val value: String?
}