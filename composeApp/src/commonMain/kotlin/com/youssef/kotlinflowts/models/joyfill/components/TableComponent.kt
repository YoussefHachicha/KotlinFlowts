package com.youssef.kotlinflowts.models.joyfill.components

import com.youssef.kotlinflowts.editor.joyfill.editors.ListBasedComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.table.RowCollection
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.components.core.ListBasedComponent
import com.youssef.kotlinflowts.models.joyfill.components.core.ValueBasedComponent
import com.youssef.kotlinflowts.models.joyfill.components.table.Column
import com.youssef.kotlinflowts.models.joyfill.components.table.Row

interface TableComponent : ListBasedComponent<Row> {
    val columns: MutableList<Column>
    val columnsOrder: MutableList<String>
    val rowOrder: MutableList<String>
}
