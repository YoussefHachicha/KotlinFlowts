package com.youssef.kotlinflowts.events.kotlinflowts.internal

import com.youssef.kotlinflowts.events.kotlinflowts.Change
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeLog
import com.youssef.kotlinflowts.events.kotlinflowts.Target
import com.youssef.kotlinflowts.events.kotlinflowts.toFieldChange
import com.youssef.kotlinflowts.events.kotlinflowts.toUnknownChange
import com.youssef.kotlinflowts.models.kotlinflowts.components.AbstractMappable
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID

internal class ChangeLogImpl(wrapped: MutableMap<String, Any?>) : AbstractMappable(wrapped),
    ChangeLog {
    override val id: String get() = wrapped[ID] as String
    override val identifier: String get() = wrapped[ChangeLog::identifier.name] as String
    override val fieldId: String get() = wrapped[ChangeLog::fieldId.name] as String
    override val fieldIdentifier: String get() = wrapped[ChangeLog::fieldIdentifier.name] as String
    override val pageId: String get() = wrapped[ChangeLog::pageId.name] as String
    override val fileId: String get() = wrapped[ChangeLog::fileId.name] as String
    override val fieldPositionId: String get() = wrapped[ChangeLog::fieldPositionId.name] as String
    override val target: String get() = wrapped[ChangeLog::target.name] as String
    override val createdOn: Long get() = wrapped[ChangeLog::createdOn.name] as Long
    override val change: Change
        get() {
            val type = target
            val change = (wrapped[ChangeLog::change.name] as MutableMap<String, Any?>)
            return when (type) {
                Target.field_update -> change.toFieldChange()
                else -> change.toUnknownChange()
            }
        }
    override val sdk: String get() = wrapped[ChangeLog::sdk.name] as String
    override val v: Int get() = wrapped[ChangeLog::v.name] as Int
}