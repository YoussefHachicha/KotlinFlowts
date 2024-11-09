package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.RichTextComponentEditor
import com.youssef.kotlinflowts.models.kotlinflowts.components.RichTextComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal open class RichTextComponentEditorImpl(
    app: App,
    override val comp: RichTextComponent,
) : AnyComponentEditor<RichTextComponent>(app,comp,null), RichTextComponentEditor