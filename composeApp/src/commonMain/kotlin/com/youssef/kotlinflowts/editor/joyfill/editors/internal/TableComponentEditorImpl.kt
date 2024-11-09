package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.TableComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.table.internal.RowCollectionImpl
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.components.TableComponent
import com.youssef.kotlinflowts.models.joyfill.components.table.Row
import com.youssef.kotlinflowts.models.joyfill.utils.App

@PublishedApi
internal class TableComponentEditorImpl(
    app: App,
    override val comp: TableComponent,
    identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractListBasedComponentEditor<Row>(app, comp, onChange), TableComponentEditor {
    override val rows = RowCollectionImpl(app, comp, this, identity, onChange)
}



