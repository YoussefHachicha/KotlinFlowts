package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.BlockComponentEditor
import com.youssef.kotlinflowts.models.kotlinflowts.components.BlockComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal open class BlockComponentEditorImpl(
    app: App,
    override val comp: BlockComponent,
) : AnyComponentEditor<BlockComponent>(app,comp,null), BlockComponentEditor