package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2

interface DropdownEditor {
    val options: List<Option2>

    fun select(key: String?)

    fun select(option: Option2?)

    fun getSelectedOption(): Option2?

    val selected: Option2?

    fun addOption(value: String)

    fun removeOption(id: String)
}