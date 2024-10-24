package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.MultiSelectFieldEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.MultiSelectField
import com.youssef.kotlinflowts.models.joyfill.utils.Document
import com.youssef.kotlinflowts.models.joyfill.utils.Option2

@PublishedApi
internal class MultiSelectFieldEditorImpl(
    document: Document,
    override val field: MultiSelectField,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyFieldEditor<MultiSelectField>(document, field, onChange), MultiSelectFieldEditor {

    override val options: List<Option2> get() = this.field.options

    private fun look(key: String?): Option2? = this.field.options.firstOrNull { it.id == key || it.value == key }

    override fun selected(): List<Option2> {
        val found = mutableListOf<Option2>()
        val value = this.field.value ?: return emptyList()
        for (option in value) found.add(look(option) ?: continue)
        return found
    }

    override fun select(key: String?) {
        val option = look(key) ?: return
        val selected = this.field.value.toMutableSet()
        if (selected.contains(option.id)) return
        field.value.add(option.id)
        notifyChange()
    }

    private fun notifyChange() {
        notifyChange(field.value)
    }

    override fun set(options: List<Option2>) {
        val pool = field.options.map { it.id }
        val candidates = options.filter { pool.contains(it.id) }.toSet().map { it.id }.toMutableList()
        field.value.clear()
        field.value.addAll(candidates)
        notifyChange()
    }

    override fun unselect(key: String?) {
        val option = look(key) ?: return
        val selected = this.field.value.toMutableSet()
        if (!selected.contains(option.id)) return
        field.value.remove(option.id)
        notifyChange()
    }

    override fun unselect(option: Option2?) = unselect(option?.id)

    override fun select(option: Option2?) = select(option?.id)
}