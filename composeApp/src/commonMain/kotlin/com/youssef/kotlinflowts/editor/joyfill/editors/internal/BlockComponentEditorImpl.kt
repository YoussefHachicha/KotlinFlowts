package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.BlockComponentEditor
import com.youssef.kotlinflowts.models.joyfill.components.BlockComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal open class BlockComponentEditorImpl(
    app: App,
    override val comp: BlockComponent,
) : AnyComponentEditor<BlockComponent>(app,comp,null), BlockComponentEditor