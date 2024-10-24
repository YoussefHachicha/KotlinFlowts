package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.utils.Option2

interface DropdownEditor {
    val options: List<Option2>

    fun select(key: String?)

    fun select(option: Option2?)

    fun selected(): Option2?
}