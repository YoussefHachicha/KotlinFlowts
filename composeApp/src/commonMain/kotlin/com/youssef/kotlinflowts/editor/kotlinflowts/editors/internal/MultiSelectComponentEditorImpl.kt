package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.MultiSelectComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.MultiSelectComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2
import com.youssef.kotlinflowts.models.kotlinflowts.utils.option

@PublishedApi
internal class MultiSelectComponentEditorImpl(
    app: App,
    override val comp: MultiSelectComponent,
    val identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyComponentEditor<MultiSelectComponent>(app, comp, onChange), MultiSelectComponentEditor {

    private val _options = mutableStateListOf<Option2>()
    override val options: List<Option2> = _options

    init {
        _options.addAll(comp.options)
    }

    private fun look(key: String?): Option2? = _options.firstOrNull { it.id == key || it.value == key }

    override var selected: Option2? by mutableStateOf(null)

    override fun selected(): List<Option2> {
        val found = mutableListOf<Option2>()
        val value = this.comp.value ?: return emptyList()
        for (option in value) found.add(look(option) ?: continue)
        return found
    }

    private fun String.toOption() = option(id = identity.generate(), value = this)

    override fun addOption(value: String) {
        if (value.isEmpty()) return
        val option = value.toOption()
        if (_options.contains(option)) return
        _options.add(option)
        comp.options.add(option)
        notifyChange(_options.map { it.toMap() }.toMutableList())
    }

    override fun removeOption(id: String) {
        if (!_options.map { it.id }.contains(id)) return
        if (_options.removeIf { it.id == id }) {
            comp.options.removeIf { it.id == id }
            selected = null
            notifyChange(_options.map { it.toMap() }.toMutableList())
        }
    }

    override var multiple: Boolean by mutableStateOf(comp.multiple)

    override fun select(key: String?) {
        val option = look(key) ?: return
        selected = option
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

    override fun generateCode(): String {
        return """
            Text(
                text = "Multi Select",
            )
        """.trimIndent()
    }
}