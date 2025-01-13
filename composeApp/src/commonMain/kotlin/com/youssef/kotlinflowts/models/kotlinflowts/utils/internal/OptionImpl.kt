package com.youssef.kotlinflowts.models.kotlinflowts.utils.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.AbstractMappable
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2

internal class OptionImpl(wrapped: MutableMap<String, Any?>) : AbstractMappable(wrapped), Option2 {
    override val id: String get() = wrapped[ID] as String
    override val deleted: Boolean get() = (wrapped[Option2::deleted.name] as? Boolean) == true
    override val value: String get() = wrapped[Option2::value.name] as String

    companion object {
        fun create(id: String, value: String, deleted: Boolean = false): Option2 {
            return OptionImpl(mutableMapOf(ID to id, Option2::value.name to value, Option2::deleted.name to deleted))
        }
    }
}