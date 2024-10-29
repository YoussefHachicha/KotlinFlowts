package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.ColumnComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.components.core.ListBasedComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal class ColumnComponentEditorImpl(
    app: App,
    override val component: ListBasedComponent<Component>,
    identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?,
) : AbstractListBasedComponentEditor<Component>(app, component, onChange), ColumnComponentEditor
