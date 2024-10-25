package com.youssef.kotlinflowts.models.joyfill.utils.internal

import com.youssef.kotlinflowts.models.joyfill.components.AbstractMappable
import com.youssef.kotlinflowts.models.joyfill.utils.ID
import com.youssef.kotlinflowts.models.joyfill.utils.Option2

internal class OptionImpl(wrapped: MutableMap<String, Any?>) : AbstractMappable(wrapped), Option2 {
    override val id: String get() = wrapped[ID] as String
    override val deleted: Boolean get() = (wrapped[Option2::deleted.name] as? Boolean) == true
    override val value: String get() = wrapped[Option2::value.name] as String
}