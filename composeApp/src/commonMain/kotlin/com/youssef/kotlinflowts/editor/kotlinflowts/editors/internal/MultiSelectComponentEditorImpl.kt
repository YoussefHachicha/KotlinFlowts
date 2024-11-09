package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.MultiSelectComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.MultiSelectComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2

@PublishedApi
internal class MultiSelectComponentEditorImpl(
    app: App,
    override val comp: MultiSelectComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyComponentEditor<MultiSelectComponent>(app, comp, onChange), MultiSelectComponentEditor {

    override val options: List<Option2> get() = this.comp.options

    private fun look(key: String?): Option2? = this.comp.options.firstOrNull { it.id == key || it.value == key }

    override fun selected(): List<Option2> {
        val found = mutableListOf<Option2>()
        val value = this.comp.value ?: return emptyList()
        for (option in value) found.add(look(option) ?: continue)
        return found
    }

    override fun select(key: String?) {
        val option = look(key) ?: return
        val selected = this.comp.value.toMutableSet()
        if (selected.contains(option.id)) return
        comp.value.add(option.id)
        notifyChange()
    }

    private fun notifyChange() {
        notifyChange(comp.value)
    }

    override fun set(options: List<Option2>) {
        val pool = comp.options.map { it.id }
        val candidates = options.filter { pool.contains(it.id) }.toSet().map { it.id }.toMutableList()
        comp.value.clear()
        comp.value.addAll(candidates)
        notifyChange()
    }

    override fun unselect(key: String?) {
        val option = look(key) ?: return
        val selected = this.comp.value.toMutableSet()
        if (!selected.contains(option.id)) return
        comp.value.remove(option.id)
        notifyChange()
    }

    override fun unselect(option: Option2?) = unselect(option?.id)

    override fun select(option: Option2?) = select(option?.id)
}