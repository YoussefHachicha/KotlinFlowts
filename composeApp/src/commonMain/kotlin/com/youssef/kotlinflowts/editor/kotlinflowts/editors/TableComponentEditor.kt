package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.editor.kotlinflowts.table.RowCollection
import com.youssef.kotlinflowts.models.kotlinflowts.components.TableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Row

interface TableComponentEditor : ListBasedComponentEditor<Row> {
    override val comp: TableComponent
    val rows: RowCollection
}


