package com.youssef.kotlinflowts.editor.kotlinflowts.column.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.LayoutCollection
import com.youssef.kotlinflowts.editor.kotlinflowts.column.ColumnComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal.AbstractListBasedComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.internal.CodeFormatter
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.ColumnComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class ColumnComponentEditorImpl(
    app: App,
    override val comp: ColumnComponent,
    val identity: IdentityGenerator,
    val onChange: ((ChangeEvent) -> Unit)?,
) : AbstractListBasedComponentEditor<Component>(app, comp, onChange), ColumnComponentEditor {
    override val columnComponents: LayoutCollection = ColumnCollectionImpl(app, identity, comp, onChange)

    override fun generateCode(): String {
        return CodeFormatter.generateContainerCode(
            containerType = comp.type,
            components = columnComponents.all()
        )
    }
}

