package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.DropdownFieldEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.DropdownField
import com.youssef.kotlinflowts.models.joyfill.utils.Document
import com.youssef.kotlinflowts.models.joyfill.utils.Option2


@PublishedApi
internal class DropdownFieldEditorImpl(
    document: Document,
    override val field: DropdownField,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyFieldEditor<DropdownField>(document, field, onChange), DropdownFieldEditor {
    override val options: List<Option2> get() = this.field.options

    private fun look(key: String?): Option2? = this.field.options.firstOrNull { it.id == key || it.value == key }

    override fun selected(): Option2? = look(this.field.value)

    override fun select(key: String?) {
        val option = look(key)
        if (field.value == option?.id) return
        field.value = option?.id
        notifyChange(option?.value)
    }

    override fun select(option: Option2?) = select(option?.id)
}