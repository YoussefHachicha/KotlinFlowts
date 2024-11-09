package com.youssef.kotlinflowts.models.kotlinflowts.components.table.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.table.DropdownColumn
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.TextColumn
import com.youssef.kotlinflowts.models.kotlinflowts.utils.JsonList
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2
import com.youssef.kotlinflowts.models.kotlinflowts.utils.toOption

@PublishedApi
internal class DropDownColumnImpl(wrapped: MutableMap<String, Any?>) : AbstractColumn(wrapped), DropdownColumn {
    override val options: List<Option2> = JsonList(wrapped[DropdownColumn::options.name]) { it.toOption() }
    override val value: String? get() = wrapped[TextColumn::value.name] as? String
}