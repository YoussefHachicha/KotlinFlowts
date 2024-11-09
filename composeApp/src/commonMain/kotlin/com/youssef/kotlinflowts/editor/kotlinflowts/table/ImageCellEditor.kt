package com.youssef.kotlinflowts.editor.kotlinflowts.table

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.FileBasedEditor
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Column

interface ImageCellEditor : CellEditor, FileBasedEditor {
    val column: Column
}