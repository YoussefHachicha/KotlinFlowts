package com.youssef.kotlinflowts.editor.joyfill.chart.internal

import com.youssef.kotlinflowts.editor.joyfill.chart.LineCollection
import com.youssef.kotlinflowts.editor.joyfill.chart.LineEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.EventTrigger
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.fields.ChartComponent
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Line
import com.youssef.kotlinflowts.models.joyfill.toLine
import com.youssef.kotlinflowts.models.joyfill.utils.App
import com.youssef.kotlinflowts.models.joyfill.utils.ID

@PublishedApi
internal class LineCollectionImpl(
    app: App,
    val identity: IdentityGenerator,
    field: ChartComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<ChartComponent>(app, field, onChange), LineCollection {

    override fun all(): List<LineEditor> = field.value?.map { LineEditorImpl(identity, it) } ?: emptyList()

    private fun look(key: String): Line? = field.value?.find { it.id == key || it.title == key }

    override fun find(key: String): LineEditor? {
        val line = look(key) ?: return null
        return LineEditorImpl(identity, line)
    }

    override fun add(
        title: String,
        id: String?,
        description: String?
    ): LineEditor {
        val line = mutableMapOf<String, Any?>(
            ID to identity.generate(),
            Line::title.name to title,
            Line::description.name to description
        ).toLine()
        field.value?.add(line)
        notifyChange(field.value?.map { it.toMap() }?.toMutableList())
        return LineEditorImpl(identity, line)
    }

    override fun remove(key: String): Line? {
        val line = look(key) ?: return null
        field.value?.remove(line)
        return null
    }
}