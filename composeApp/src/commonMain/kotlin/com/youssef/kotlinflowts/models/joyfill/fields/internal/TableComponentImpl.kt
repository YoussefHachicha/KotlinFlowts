package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.TableComponent
import com.youssef.kotlinflowts.models.joyfill.fields.table.Row
import com.youssef.kotlinflowts.models.joyfill.toColumn
import com.youssef.kotlinflowts.models.joyfill.toRow
import com.youssef.kotlinflowts.models.joyfill.utils.COLUMNS
import com.youssef.kotlinflowts.models.joyfill.utils.COLUMN_ORDER
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList

@PublishedApi
internal open class TableComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractListBasedComponent<Row>(wrapped), TableComponent {

    override fun factory(map: MutableMap<String, Any?>): Row = map.toRow()

    override val columns = JsonList(wrapped[COLUMNS]) { it.toColumn() }

    override val columnsOrder get() = wrapped[COLUMN_ORDER] as MutableList<String>

    override val rowOrder get() = wrapped[TableComponent::rowOrder.name] as MutableList<String>
}