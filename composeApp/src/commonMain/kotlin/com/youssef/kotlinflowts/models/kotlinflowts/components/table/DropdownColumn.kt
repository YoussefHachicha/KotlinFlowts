package com.youssef.kotlinflowts.models.kotlinflowts.components.table

import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2

interface DropdownColumn : Column {
    val options: List<Option2>
    val value: String?
}