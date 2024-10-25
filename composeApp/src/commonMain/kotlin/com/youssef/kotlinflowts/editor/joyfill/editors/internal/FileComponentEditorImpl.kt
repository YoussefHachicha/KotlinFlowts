package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.FileComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.components.FileComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal class FileComponentEditorImpl(
    app: App,
    override val component: FileComponent,
    identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractFileBasedComponentEditor(app, component, identity, onChange), FileComponentEditor