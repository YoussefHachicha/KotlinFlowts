package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.NumberComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.components.NumberComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal class NumberComponentEditorImpl(
    app: App,
    override val component: NumberComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<Double, NumberComponent>(app, component, onChange), NumberComponentEditor