package com.youssef.kotlinflowts.models.kotlinflowts.components.table.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.AbstractMappable
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Column
import com.youssef.kotlinflowts.models.kotlinflowts.type
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID

abstract class AbstractColumn(
    wrapped: MutableMap<String,Any?>
) : AbstractMappable(wrapped), Column {
    override val id: String get() = wrapped[ID] as String
    override val title: String get() = wrapped[Column::title.name] as String
    override val type: Component.Type get() = wrapped.type()
}