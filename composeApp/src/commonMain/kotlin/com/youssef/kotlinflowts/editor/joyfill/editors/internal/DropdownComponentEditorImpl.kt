package com.youssef.kotlinflowts.editor.joyfill.editors.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.DropdownComponentEditor
import com.youssef.kotlinflowts.events.joyfill.ChangeEvent
import com.youssef.kotlinflowts.models.joyfill.components.DropdownComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App
import com.youssef.kotlinflowts.models.joyfill.utils.Option2


@PublishedApi
internal class DropdownComponentEditorImpl(
    app: App,
    override val component: DropdownComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AnyComponentEditor<DropdownComponent>(app, component, onChange), DropdownComponentEditor {
    override val options: List<Option2> get() = this.component.options

    private fun look(key: String?): Option2? = this.component.options.firstOrNull { it.id == key || it.value == key }

    override fun selected(): Option2? = look(this.component.value)

    override fun select(key: String?) {
        val option = look(key)
        if (component.value == option?.id) return
        component.value = option?.id
        notifyChange(option?.value)
    }

    override fun select(option: Option2?) = select(option?.id)
}