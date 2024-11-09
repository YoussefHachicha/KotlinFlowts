package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.DropdownComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.components.DropdownComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App
import com.youssef.kotlinflowts.models.joyfill.utils.Option2


@PublishedApi
internal class DropdownComponentEditorImpl(
    app: App,
    override val comp: DropdownComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyComponentEditor<DropdownComponent>(app, comp, onChange), DropdownComponentEditor {
    override val options: List<Option2> get() = this.comp.options

    private fun look(key: String?): Option2? = this.comp.options.firstOrNull { it.id == key || it.value == key }

    override fun selected(): Option2? = look(this.comp.value)

    override fun select(key: String?) {
        val option = look(key)
        if (comp.value == option?.id) return
        comp.value = option?.id
        notifyChange(option?.value)
    }

    override fun select(option: Option2?) = select(option?.id)
}