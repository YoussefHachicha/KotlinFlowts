package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TableComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.table.internal.RowCollectionImpl
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.TableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Row
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class TableComponentEditorImpl(
    app: App,
    override val comp: TableComponent,
    identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractListBasedComponentEditor<Row>(app, comp, onChange), TableComponentEditor {
    override val rows = RowCollectionImpl(app, comp, this, identity, onChange)

    override fun generateCode(): String {
        //TODO
        return """
            Text("Table component")
        """.trimIndent()
    }
}



