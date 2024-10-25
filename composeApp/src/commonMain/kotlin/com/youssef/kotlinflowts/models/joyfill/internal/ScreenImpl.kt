package com.youssef.kotlinflowts.models.joyfill.internal

import com.youssef.kotlinflowts.models.joyfill.ComponentPosition
import com.youssef.kotlinflowts.models.joyfill.MutableScreen
import com.youssef.kotlinflowts.models.joyfill.Screen
import com.youssef.kotlinflowts.models.joyfill.fields.AbstractMappable
import com.youssef.kotlinflowts.models.joyfill.toPosition
import com.youssef.kotlinflowts.models.joyfill.utils.ID
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList
import com.youssef.kotlinflowts.models.joyfill.utils.POSITIONS

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