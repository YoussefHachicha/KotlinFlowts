package com.youssef.kotlinflowts.models.joyfill.components.table.internal

import com.youssef.kotlinflowts.models.joyfill.components.table.DropdownColumn
import com.youssef.kotlinflowts.models.joyfill.components.table.TextColumn
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList
import com.youssef.kotlinflowts.models.joyfill.utils.Option2
import com.youssef.kotlinflowts.models.joyfill.utils.toOption

@PublishedApi
internal class DropDownColumnImpl(wrapped: MutableMap<String, Any?>) : AbstractColumn(wrapped), DropdownColumn {
    override val options: List<Option2> = JsonList(wrapped[DropdownColumn::options.name]) { it.toOption() }
    override val value: String? get() = wrapped[TextColumn::value.name] as? String
}