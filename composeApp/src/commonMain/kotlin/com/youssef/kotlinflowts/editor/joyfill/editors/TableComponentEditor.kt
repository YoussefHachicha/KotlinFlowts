package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.editor.joyfill.table.RowCollection
import com.youssef.kotlinflowts.models.joyfill.fields.TableComponent
import com.youssef.kotlinflowts.models.joyfill.fields.table.Row

interface TableComponentEditor : ListBasedComponentEditor<Row> {
    override val component: TableComponent
    val rows: RowCollection
}