package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DateFieldComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.DateFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class DateFieldFieldComponentEditorImpl(
    app: App,
    override val comp: DateFieldComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<Long, DateFieldComponent>(app, comp, onChange), DateFieldComponentEditor {
    override fun generateCode(): String {
        return """
            Text(
                text = "Date",
            )
        """.trimIndent()
    }

    override fun changeValue(value: Long) {
        this.value = value
        notifyChange(value)
    }
}