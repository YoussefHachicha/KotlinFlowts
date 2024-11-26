package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DateComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.DateComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class DateComponentEditorImpl(
    app: App,
    override val comp: DateComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<Long, DateComponent>(app, comp, onChange), DateComponentEditor {
    override fun generateCode(): String {
        return """
            Text(
                text = "Date",
            )
        """.trimIndent()
    }
}