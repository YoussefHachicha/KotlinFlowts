package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.FileComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.FileComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class FileComponentEditorImpl(
    app: App,
    override val comp: FileComponent,
    identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractFileBasedComponentEditor(app, comp, identity, onChange), FileComponentEditor