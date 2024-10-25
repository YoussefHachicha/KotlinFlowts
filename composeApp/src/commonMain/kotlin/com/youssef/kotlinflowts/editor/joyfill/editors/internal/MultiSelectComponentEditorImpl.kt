package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.MultiSelectComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.fields.MultiSelectComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App
import com.youssef.kotlinflowts.models.joyfill.utils.Option2

@PublishedApi
internal class MultiSelectComponentEditorImpl(
    app: App,
    override val component: MultiSelectComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyComponentEditor<MultiSelectComponent>(app, component, onChange), MultiSelectComponentEditor {

    override val options: List<Option2> get() = this.component.options

    private fun look(key: String?): Option2? = this.component.options.firstOrNull { it.id == key || it.value == key }

    override fun selected(): List<Option2> {
        val found = mutableListOf<Option2>()
        val value = this.component.value ?: return emptyList()
        for (option in value) found.add(look(option) ?: continue)
        return found
    }

    override fun select(key: String?) {
        val option = look(key) ?: return
        val selected = this.component.value.toMutableSet()
        if (selected.contains(option.id)) return
        component.value.add(option.id)
        notifyChange()
    }

    private fun notifyChange() {
        notifyChange(component.value)
    }

    override fun set(options: List<Option2>) {
        val pool = component.options.map { it.id }
        val candidates = options.filter { pool.contains(it.id) }.toSet().map { it.id }.toMutableList()
        component.value.clear()
        component.value.addAll(candidates)
        notifyChange()
    }

    override fun unselect(key: String?) {
        val option = look(key) ?: return
        val selected = this.component.value.toMutableSet()
        if (!selected.contains(option.id)) return
        component.value.remove(option.id)
        notifyChange()
    }

    override fun unselect(option: Option2?) = unselect(option?.id)

    override fun select(option: Option2?) = select(option?.id)
}