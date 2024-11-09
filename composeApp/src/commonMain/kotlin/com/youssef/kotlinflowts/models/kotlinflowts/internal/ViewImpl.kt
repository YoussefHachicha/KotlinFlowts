package com.youssef.kotlinflowts.models.kotlinflowts.internal

import com.youssef.kotlinflowts.models.kotlinflowts.View
import com.youssef.kotlinflowts.models.kotlinflowts.components.AbstractMappable
import com.youssef.kotlinflowts.models.kotlinflowts.toScreen
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID
import com.youssef.kotlinflowts.models.kotlinflowts.utils.JsonList

@PublishedApi
internal class ViewImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractMappable(wrapped), View {
    override val id: String get() = wrapped[ID] as String
    override val type: String = wrapped[View::type.name] as String
    override val screens = JsonList(wrapped[View::screens.name]) { it.toScreen() }
    override val pageOrder: List<String> = wrapped[View::pageOrder.name] as List<String>
}