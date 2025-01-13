package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2

interface MultiSelectComponentEditor : ComponentEditor {

    val options: List<Option2>

    fun select(key: String?)

    val selected: Option2?

    fun select(option: Option2?)

    fun set(options: List<Option2>)

    fun unselect(key: String?)

    fun unselect(option: Option2?)

    fun getSelected(): List<Option2>

    fun addOption(value: String)

    fun removeOption(id: String)

    var multiple: Boolean

    fun changeMultiple() { multiple = !multiple }
}