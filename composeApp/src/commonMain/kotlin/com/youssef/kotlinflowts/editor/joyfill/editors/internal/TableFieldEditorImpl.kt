package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.TableFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.table.internal.RowCollectionImpl
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.fields.TableField
import com.youssef.kotlinflowts.models.joyfill.fields.table.Row
import com.youssef.kotlinflowts.models.joyfill.utils.Document

@PublishedApi
internal class TableFieldEditorImpl(
    document: Document,
    override val field: TableField,
    identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractListBasedFieldEditor<Row>(document, field, onChange), TableFieldEditor {
    override val rows = RowCollectionImpl(document, field, this, identity, onChange)
}