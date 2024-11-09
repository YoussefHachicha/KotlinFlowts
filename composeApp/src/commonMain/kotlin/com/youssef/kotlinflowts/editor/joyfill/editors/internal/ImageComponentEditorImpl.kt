package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.ImageComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.components.ImageComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal class ImageComponentEditorImpl(
    app: App,
    override val comp: ImageComponent,
    identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractFileBasedComponentEditor(app, comp, identity, onChange), ImageComponentEditor