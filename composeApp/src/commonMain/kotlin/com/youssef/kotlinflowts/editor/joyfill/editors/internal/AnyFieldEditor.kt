package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.FieldEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.Field
import com.youssef.kotlinflowts.models.joyfill.utils.Document

@PublishedApi
internal open class AnyFieldEditor<out F : Field>(
    document: Document,
    override val field: F,
    onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<F>(document, field, onChange), FieldEditor {

    override var id: String
        get() = this.field.id
        set(value) {
            TODO()
        }

    override var identifier: String
        get() = this.field.identifier
        set(value) {
            TODO()
        }

    override var title: String
        get() = this.field.title
        set(value) {
            TODO()
        }

    override val type get() = field.type
}