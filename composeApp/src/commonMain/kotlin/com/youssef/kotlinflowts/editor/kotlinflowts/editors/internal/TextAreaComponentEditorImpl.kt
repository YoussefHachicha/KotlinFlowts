package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextAreaComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextAreaComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class TextAreaComponentEditorImpl(
    app: App,
    override val comp: TextAreaComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<String,TextAreaComponent>(app,comp,onChange), TextAreaComponentEditor