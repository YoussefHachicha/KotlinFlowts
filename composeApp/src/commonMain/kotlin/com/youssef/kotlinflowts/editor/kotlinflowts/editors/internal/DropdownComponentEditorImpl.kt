package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import androidx.compose.runtime.mutableStateListOf
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

    private fun look(key: String?): Option2? = this.comp.options.firstOrNull { it.id == key || it.value == key }

    override fun selected(): Option2? = look(this.comp.value)

    private fun String.toOption() = option(id = identity.generate(), value = this)

    override fun addOption(value: String) {
        val option = value.toOption()
        if (_options.contains(option)) return
        _options.add(option)
        notifyChange(option.value)
    }

    override fun removeOption(id: String) {
        if (!_options.map { it.id }.contains(id)) return
        _options.removeIf { it.id == id }
        notifyChange(_options.firstOrNull()?.value)
    }

    override fun select(key: String?) {
        val option = look(key)
        if (comp.value == option?.id) return
        comp.value = option?.id
        notifyChange(option?.value)
    }

    override fun select(option: Option2?) = select(option?.id)

    override fun generateCode(): String {
        return """
            Text(
                text = "Dropdown",
            )
        """.trimIndent()
    }
}