package com.youssef.kotlinflowts.editor.kotlinflowts.table

import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Row

interface RowEditor {
    val row: Row
    fun text(key: String): TextCellEditor?
    fun text(index: Int): TextCellEditor?

    fun image(key: String): ImageCellEditor?
    fun image(index: Int): ImageCellEditor?

    fun dropdown(key: String): DropdownCellEditor?
    fun dropdown(index: Int): DropdownCellEditor?

    fun col(key: String): CellEditor?
    fun col(index: Int): CellEditor?
}