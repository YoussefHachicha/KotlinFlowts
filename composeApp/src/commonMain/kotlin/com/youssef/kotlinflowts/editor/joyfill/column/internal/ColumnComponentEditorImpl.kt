package com.youssef.kotlinflowts.editor.joyfill.column.internal

import com.youssef.kotlinflowts.editor.joyfill.collections.CompCollection
import com.youssef.kotlinflowts.editor.joyfill.column.ColumnCollection
import com.youssef.kotlinflowts.editor.joyfill.column.ColumnComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.AbstractListBasedComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.internal.CompCollectionImpl
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.components.ColumnComponent
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.components.core.ListBasedComponent
import com.youssef.kotlinflowts.models.joyfill.toMutableApp
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal class ColumnComponentEditorImpl(
    app: App,
    override val component: ColumnComponent,
    val identity: IdentityGenerator,
    val onChange: ((ChangeEvent) -> Unit)?,
) : AbstractListBasedComponentEditor<Component>(app, component, onChange), ColumnComponentEditor {
    override val columnComponents: ColumnCollection = ColumnCollectionImpl(app, identity, component, onChange)
}
