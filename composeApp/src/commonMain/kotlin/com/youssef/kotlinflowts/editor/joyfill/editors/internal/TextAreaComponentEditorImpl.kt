package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.TextAreaComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.TextAreaComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal class TextAreaComponentEditorImpl(
    app: App,
    override val component: TextAreaComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<String,TextAreaComponent>(app,component,onChange), TextAreaComponentEditor