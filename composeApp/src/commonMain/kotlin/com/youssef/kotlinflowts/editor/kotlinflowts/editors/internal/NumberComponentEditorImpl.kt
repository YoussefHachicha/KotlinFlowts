package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.NumberComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.NumberComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class NumberComponentEditorImpl(
    app: App,
    override val comp: NumberComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<Double, NumberComponent>(app, comp, onChange), NumberComponentEditor