package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.DateComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.DateComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal class DateComponentEditorImpl(
    app: App,
    override val component: DateComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<Long, DateComponent>(app, component, onChange), DateComponentEditor