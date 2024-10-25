package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.editor.joyfill.table.RowCollection
import com.youssef.kotlinflowts.models.joyfill.components.TableComponent
import com.youssef.kotlinflowts.models.joyfill.components.table.Row

interface TableComponentEditor : ListBasedComponentEditor<Row> {
    override val component: TableComponent
    val rows: RowCollection
}