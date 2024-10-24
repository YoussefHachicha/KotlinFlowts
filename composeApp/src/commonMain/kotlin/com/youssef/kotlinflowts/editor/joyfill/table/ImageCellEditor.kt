package com.youssef.kotlinflowts.editor.joyfill.table

import com.youssef.kotlinflowts.editor.joyfill.editors.FileBasedEditor
import com.youssef.kotlinflowts.models.joyfill.fields.table.Column

interface ImageCellEditor : CellEditor, FileBasedEditor {
    val column: Column
}