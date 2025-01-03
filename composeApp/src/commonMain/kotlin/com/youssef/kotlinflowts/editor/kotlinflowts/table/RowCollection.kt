package com.youssef.kotlinflowts.editor.kotlinflowts.table

import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Row

interface RowCollection {
    fun all(): List<RowEditor>
    fun append(): RowEditor
    fun addAfter(index: Int): RowEditor
    fun addAfter(id: String): RowEditor
    fun addBefore(index: Int): RowEditor
    fun addBefore(id: String): RowEditor
    fun addAt(index: Int) : RowEditor
    fun get(index: Int): RowEditor?
    fun get(id: String): RowEditor?

    fun deleteAt(index: Int) : Row?
}