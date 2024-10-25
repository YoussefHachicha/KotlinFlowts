package com.youssef.kotlinflowts.models.joyfill.components.table.internal

import com.youssef.kotlinflowts.models.joyfill.components.AbstractMappable
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.components.table.Column
import com.youssef.kotlinflowts.models.joyfill.type
import com.youssef.kotlinflowts.models.joyfill.utils.ID

abstract class AbstractColumn(
    wrapped: MutableMap<String,Any?>
) : AbstractMappable(wrapped), Column {
    override val id: String get() = wrapped[ID] as String
    override val title: String get() = wrapped[Column::title.name] as String
    override val type: Component.Type get() = wrapped.type()
}