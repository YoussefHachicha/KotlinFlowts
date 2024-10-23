package com.youssef.kotlinflowts.models.joyfill.internal

import com.youssef.kotlinflowts.models.joyfill.FieldPosition
import com.youssef.kotlinflowts.models.joyfill.fields.AbstractMappable
import com.youssef.kotlinflowts.models.joyfill.utils.ID

@PublishedApi
internal class FieldPositionImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractMappable(wrapped), FieldPosition {
    override val id: String get() = wrapped[ID] as String
    override val field: String get() = wrapped[FieldPosition::field.name] as String
    override val displayType: String? get() = wrapped[FieldPosition::displayType.name] as? String
    override val y get() = wrapped[FieldPosition::y.name] as? Double ?: 0.0
    override val x get() = wrapped[FieldPosition::x.name] as? Double ?: 0.0
    override val format get() = wrapped[FieldPosition::format.name] as? String

    override val fontColor: String? get() = wrapped[FieldPosition::fontColor.name] as? String
    override val fontWeight: String? get() = wrapped[FieldPosition::fontWeight.name] as? String
    override val fontSize: Double? get() = wrapped[FieldPosition::fontSize.name] as? Double
    override val textTransform: String? get() = wrapped[FieldPosition::textTransform.name] as? String
    override val textOverflow: String? get() = wrapped[FieldPosition::textOverflow.name] as? String
    override val textAlign: String? get() = wrapped[FieldPosition::textAlign.name] as? String
}