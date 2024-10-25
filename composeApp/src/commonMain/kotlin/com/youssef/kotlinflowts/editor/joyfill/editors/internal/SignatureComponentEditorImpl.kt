package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.SignatureComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.components.SignatureComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal class SignatureComponentEditorImpl(
    app: App,
    override val component: SignatureComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<String, SignatureComponent>(app, component, onChange), SignatureComponentEditor