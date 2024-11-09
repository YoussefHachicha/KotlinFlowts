package com.youssef.kotlinflowts.models.kotlinflowts.components

import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ListBasedComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Column
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Row

interface TableComponent : ListBasedComponent<Row> {
    val columns: MutableList<Column>
    val columnsOrder: MutableList<String>
    val rowOrder: MutableList<String>
}
