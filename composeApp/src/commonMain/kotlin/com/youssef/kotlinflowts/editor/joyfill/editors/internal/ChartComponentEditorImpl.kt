package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.chart.LineCollection
import com.youssef.kotlinflowts.editor.joyfill.chart.internal.LineCollectionImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.ChartComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.components.ChartComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal class ChartComponentEditorImpl(
    app: App,
    field: ChartComponent,
    identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyComponentEditor<ChartComponent>(app, field, onChange), ChartComponentEditor {
    override val lines: LineCollection = LineCollectionImpl(app, identity, field, onChange)
}