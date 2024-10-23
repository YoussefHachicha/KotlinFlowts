package com.youssef.kotlinflowts.models.joyfill.fields.table.internal

import com.youssef.kotlinflowts.models.joyfill.fields.AbstractMappable
import com.youssef.kotlinflowts.models.joyfill.fields.Field
import com.youssef.kotlinflowts.models.joyfill.fields.table.Column
import com.youssef.kotlinflowts.models.joyfill.type
import com.youssef.kotlinflowts.models.joyfill.utils.ID

abstract class AbstractColumn(
    wrapped: MutableMap<String,Any?>
) : AbstractMappable(wrapped), Column {
    override val id: String get() = wrapped[ID] as String
    override val title: String get() = wrapped[Column::title.name] as String
    override val type: Field.Type get() = wrapped.type()
}