package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.TextComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.components.TextComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal class TextComponentEditorImpl(
    app: App,
    override val component: TextComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<String,TextComponent>(app,component,onChange), TextComponentEditor