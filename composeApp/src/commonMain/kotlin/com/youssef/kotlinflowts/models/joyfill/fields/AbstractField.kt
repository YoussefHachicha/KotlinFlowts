package com.youssef.kotlinflowts.models.joyfill.fields

import com.youssef.kotlinflowts.models.joyfill.type
import com.youssef.kotlinflowts.models.joyfill.utils.ID

internal abstract class AbstractField(
    wrapped: MutableMap<String, Any?>
) : AbstractMappable(wrapped), Field {
    override val id: String get() = wrapped[ID] as String
    override val title: String get() = wrapped[Field::title.name] as String
    override val identifier: String get() = wrapped[Field::identifier.name] as String
    override val type: Field.Type get() = wrapped.type()
    override val disabled: Boolean get() = wrapped[Field::disabled.name] as? Boolean == true
}