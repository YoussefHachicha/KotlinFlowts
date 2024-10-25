package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.RichTextComponentEditor
import com.youssef.kotlinflowts.models.joyfill.components.RichTextComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal open class RichTextComponentEditorImpl(
    app: App,
    override val component: RichTextComponent,
) : AnyComponentEditor<RichTextComponent>(app,component,null), RichTextComponentEditor