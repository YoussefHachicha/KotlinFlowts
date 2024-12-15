package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DropdownComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.DropdownComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2
import com.youssef.kotlinflowts.models.kotlinflowts.utils.option


@PublishedApi
internal class DropdownComponentEditorImpl(
    app: App,
    override val comp: DropdownComponent,
    val identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyComponentEditor<DropdownComponent>(app, comp, onChange), DropdownComponentEditor {

    private val _options = mutableStateListOf<Option2>()
    override val options: List<Option2> = _options

    init {
        _options.addAll(comp.options)
    }

    private fun look(key: String?): Option2? = options.firstOrNull { it.id == key || it.value == key }

    override fun selected(): Option2? = look(this.comp.value)

    override var selected: Option2? by mutableStateOf(null)

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

    override fun select(key: String?) {
        val option = look(key)
        selected = option
        if (comp.value == option?.id) return
        comp.value = option?.id
        notifyChange(option?.value)
    }

    override fun select(option: Option2?) = select(option?.id)

    override var multiple: Boolean by mutableStateOf(comp.multiple)

    override fun generateCode(): String {
        return """
            Text(
                text = "Dropdown",
            )
        """.trimIndent()
    }
}