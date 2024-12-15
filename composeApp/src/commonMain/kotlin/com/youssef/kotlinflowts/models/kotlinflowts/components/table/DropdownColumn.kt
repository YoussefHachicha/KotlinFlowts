package com.youssef.kotlinflowts.models.kotlinflowts.components.table

import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2

interface DropdownColumn : Column {
    val options: MutableList<Option2>
    val value: String?
}