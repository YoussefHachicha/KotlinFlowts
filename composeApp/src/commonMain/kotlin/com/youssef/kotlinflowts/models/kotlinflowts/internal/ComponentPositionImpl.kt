package com.youssef.kotlinflowts.models.kotlinflowts.internal

import com.youssef.kotlinflowts.models.kotlinflowts.ComponentPosition
import com.youssef.kotlinflowts.models.kotlinflowts.components.AbstractMappable
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID

@PublishedApi
internal class ComponentPositionImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractMappable(wrapped), ComponentPosition {
    override val id: String get() = wrapped[ID] as String
    override val componentId: String get() = wrapped[ComponentPosition::componentId.name] as String
    override val displayType: String? get() = wrapped[ComponentPosition::displayType.name] as? String
    override val depth: Int get() = wrapped[ComponentPosition::depth.name] as Int
    override val y get() = wrapped[ComponentPosition::y.name] as? Double ?: 0.0
    override val x get() = wrapped[ComponentPosition::x.name] as? Double ?: 0.0
    override val format get() = wrapped[ComponentPosition::format.name] as? String
    override val builderId get() = wrapped[ComponentPosition::builderId.name] as String

    override val fontColor: String? get() = wrapped[ComponentPosition::fontColor.name] as? String
    override val fontWeight: String? get() = wrapped[ComponentPosition::fontWeight.name] as? String
    override val fontSize: Double? get() = wrapped[ComponentPosition::fontSize.name] as? Double
    override val textTransform: String? get() = wrapped[ComponentPosition::textTransform.name] as? String
    override val textOverflow: String? get() = wrapped[ComponentPosition::textOverflow.name] as? String
    override val textAlign: String? get() = wrapped[ComponentPosition::textAlign.name] as? String
}