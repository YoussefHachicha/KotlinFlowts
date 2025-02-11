package com.youssef.kotlinflowts.editor.kotlinflowts.chart.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.chart.LineCollection
import com.youssef.kotlinflowts.editor.kotlinflowts.chart.LineEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.EventTrigger
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.ChartComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Line
import com.youssef.kotlinflowts.models.kotlinflowts.toLine
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID

@PublishedApi
internal class LineCollectionImpl(
    app: App,
    val identity: IdentityGenerator,
    field: ChartComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<ChartComponent>(app, field, onChange), LineCollection {

    override fun all(): List<LineEditor> = component.value.map { LineEditorImpl(identity, it) }

    private fun look(key: String): Line? = component.value.find { it.id == key || it.title == key }

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

        component.value.add(line)
        notifyChange(component.value.map { it.toMap() }.toMutableList())
        return LineEditorImpl(identity, line)
    }

    override fun remove(key: String): Line? {
        val line = look(key) ?: return null
        component.value.remove(line)
        return null
    }
}