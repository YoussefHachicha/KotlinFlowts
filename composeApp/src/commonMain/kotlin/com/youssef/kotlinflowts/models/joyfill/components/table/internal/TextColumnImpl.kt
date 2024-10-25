package com.youssef.kotlinflowts.models.joyfill.components.table.internal

import com.youssef.kotlinflowts.models.joyfill.components.table.TextColumn

@PublishedApi
internal class TextColumnImpl(wrapped: MutableMap<String,Any?>) : AbstractColumn(wrapped), TextColumn {
    override val value: String? get() = wrapped[TextColumn::value.name] as? String
}