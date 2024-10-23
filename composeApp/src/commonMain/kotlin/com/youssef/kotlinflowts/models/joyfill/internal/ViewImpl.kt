package com.youssef.kotlinflowts.models.joyfill.internal

import com.youssef.kotlinflowts.models.joyfill.View
import com.youssef.kotlinflowts.models.joyfill.fields.AbstractMappable
import com.youssef.kotlinflowts.models.joyfill.toPage
import com.youssef.kotlinflowts.models.joyfill.utils.ID
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList

@PublishedApi
internal class ViewImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractMappable(wrapped), View {
    override val id: String get() = wrapped[ID] as String
    override val type: String = wrapped[View::type.name] as String
    override val pages = JsonList(wrapped[View::pages.name]) { it.toPage() }
    override val pageOrder: List<String> = wrapped[View::pageOrder.name] as List<String>
}