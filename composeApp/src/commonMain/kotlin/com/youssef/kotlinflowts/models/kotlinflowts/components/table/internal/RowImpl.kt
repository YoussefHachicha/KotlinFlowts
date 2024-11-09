package com.youssef.kotlinflowts.models.kotlinflowts.components.table.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.AbstractMappable
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Row
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID

@PublishedApi
internal class RowImpl(wrapped: MutableMap<String, Any?>) : AbstractMappable(wrapped), Row {
    override val id: String get() = wrapped[ID] as String
    override val cells: MutableMap<String, Any?> = wrapped[Row::cells.name] as MutableMap<String, Any?>
    override val deleted: Boolean get() = wrapped[Row::deleted.name] as? Boolean == true
}