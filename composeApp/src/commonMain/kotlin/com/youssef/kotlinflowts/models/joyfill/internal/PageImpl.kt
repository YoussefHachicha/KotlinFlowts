package com.youssef.kotlinflowts.models.joyfill.internal

import com.youssef.kotlinflowts.models.joyfill.FieldPosition
import com.youssef.kotlinflowts.models.joyfill.MutablePage
import com.youssef.kotlinflowts.models.joyfill.Page
import com.youssef.kotlinflowts.models.joyfill.fields.AbstractMappable
import com.youssef.kotlinflowts.models.joyfill.toPosition
import com.youssef.kotlinflowts.models.joyfill.utils.ID
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList
import com.youssef.kotlinflowts.models.joyfill.utils.POSITIONS

@PublishedApi
internal class PageImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractMappable(wrapped), MutablePage {
    override var id: String
        get() = wrapped[ID] as String
        set(value) {
            wrapped[ID] = value
        }
    override var name: String
        get() = wrapped[Page::name.name] as String
        set(value) {
            wrapped[Page::name.name] = value
        }

    override val positions: MutableList<FieldPosition> = JsonList(wrapped[POSITIONS]) { it.toPosition() }
}