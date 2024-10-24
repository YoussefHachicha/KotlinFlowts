package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.editor.joyfill.table.RowCollection
import com.youssef.kotlinflowts.models.joyfill.fields.TableField
import com.youssef.kotlinflowts.models.joyfill.fields.table.Row

interface TableFieldEditor : ListBasedFieldEditor<Row> {
    override val field: TableField
    val rows: RowCollection
}