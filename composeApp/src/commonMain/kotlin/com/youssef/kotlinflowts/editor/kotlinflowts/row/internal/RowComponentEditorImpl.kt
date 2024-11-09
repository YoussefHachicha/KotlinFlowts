package com.youssef.kotlinflowts.editor.kotlinflowts.row.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.LayoutCollection
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.AbstractListBasedComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.row.RowComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.RowComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class RowComponentEditorImpl(
    app: App,
    override val comp: RowComponent,
    val identity: IdentityGenerator,
    val onChange: ((ChangeEvent) -> Unit)?,
) : AbstractListBasedComponentEditor<Component>(app, comp, onChange), RowComponentEditor {
    override val rowComponents: LayoutCollection = RowCollectionImpl(app, identity, comp, onChange)
}
