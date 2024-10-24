package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.utils.Option2

interface MultiSelectFieldEditor : FieldEditor {

    val options: List<Option2>

    fun select(key: String?)

    fun select(option: Option2?)

    fun set(options: List<Option2>)

    fun unselect(key: String?)

    fun unselect(option: Option2?)

    fun selected(): List<Option2>
}