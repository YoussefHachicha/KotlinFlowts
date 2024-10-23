package com.youssef.kotlinflowts.models.joyfill.fields

import com.youssef.kotlinflowts.models.joyfill.fields.table.Column
import com.youssef.kotlinflowts.models.joyfill.fields.table.Row

interface TableField : ListBasedField<Row> {
    val columns: MutableList<Column>
    val columnsOrder: MutableList<String>
    val rowOrder: MutableList<String>
}