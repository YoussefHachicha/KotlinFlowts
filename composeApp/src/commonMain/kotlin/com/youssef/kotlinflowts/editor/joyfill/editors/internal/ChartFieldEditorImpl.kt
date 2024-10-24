package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.chart.LineCollection
import com.youssef.kotlinflowts.editor.joyfill.chart.internal.LineCollectionImpl
import com.youssef.kotlinflowts.editor.joyfill.editors.ChartFieldEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.fields.ChartField
import com.youssef.kotlinflowts.models.joyfill.utils.Document

@PublishedApi
internal class ChartFieldEditorImpl(
    document: Document,
    field: ChartField,
    identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyFieldEditor<ChartField>(document, field, onChange), ChartFieldEditor {
    override val lines: LineCollection = LineCollectionImpl(document, identity, field, onChange)
}