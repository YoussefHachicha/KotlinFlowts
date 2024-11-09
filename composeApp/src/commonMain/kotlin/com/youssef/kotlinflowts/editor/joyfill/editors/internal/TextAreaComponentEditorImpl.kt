package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.TextAreaComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.components.TextAreaComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal class TextAreaComponentEditorImpl(
    app: App,
    override val comp: TextAreaComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<String,TextAreaComponent>(app,comp,onChange), TextAreaComponentEditor