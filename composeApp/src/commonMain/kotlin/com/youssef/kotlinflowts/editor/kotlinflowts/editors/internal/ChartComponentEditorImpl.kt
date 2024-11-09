package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.chart.LineCollection
import com.youssef.kotlinflowts.editor.kotlinflowts.chart.internal.LineCollectionImpl
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ChartComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.ChartComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class ChartComponentEditorImpl(
    app: App,
    field: ChartComponent,
    identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyComponentEditor<ChartComponent>(app, field, onChange), ChartComponentEditor {
    override val lines: LineCollection = LineCollectionImpl(app, identity, field, onChange)
}