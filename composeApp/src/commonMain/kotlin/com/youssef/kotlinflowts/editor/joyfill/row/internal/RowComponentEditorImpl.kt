package com.youssef.kotlinflowts.editor.joyfill.row.internal

import com.youssef.kotlinflowts.editor.joyfill.LayoutCollection
import com.youssef.kotlinflowts.editor.joyfill.editors.internal.AbstractListBasedComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.row.RowComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.components.RowComponent
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal class RowComponentEditorImpl(
    app: App,
    override val comp: RowComponent,
    val identity: IdentityGenerator,
    val onChange: ((ChangeEvent) -> Unit)?,
) : AbstractListBasedComponentEditor<Component>(app, comp, onChange), RowComponentEditor {
    override val rowComponents: LayoutCollection = RowCollectionImpl(app, identity, comp, onChange)
}
