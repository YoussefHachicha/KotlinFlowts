package com.youssef.kotlinflowts.models.kotlinflowts.internal

import com.youssef.kotlinflowts.models.kotlinflowts.ComponentPosition
import com.youssef.kotlinflowts.models.kotlinflowts.MutableScreen
import com.youssef.kotlinflowts.models.kotlinflowts.Screen
import com.youssef.kotlinflowts.models.kotlinflowts.components.AbstractMappable
import com.youssef.kotlinflowts.models.kotlinflowts.toPosition
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID
import com.youssef.kotlinflowts.models.kotlinflowts.utils.JsonList
import com.youssef.kotlinflowts.models.kotlinflowts.utils.POSITIONS

@PublishedApi
internal class ScreenImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractMappable(wrapped), MutableScreen {
    override var id: String
        get() = wrapped[ID] as String
        set(value) {
            wrapped[ID] = value
        }
    override var name: String
        get() = wrapped[Screen::name.name] as String
        set(value) {
            wrapped[Screen::name.name] = value
        }

    override val positions: MutableList<ComponentPosition> = JsonList(wrapped[POSITIONS]) { it.toPosition() }
}