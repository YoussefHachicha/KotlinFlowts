package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextFieldComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class TextComponentEditorImpl(
    app: App,
    override val comp: TextComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<String, TextComponent>(app,comp,onChange), TextComponentEditor {
    override fun generateCode(): String {
        return """
            Text(
                text = "${value.orEmpty()}",
                modifier = Modifier.fillMaxWidth()
            )
        """.trimIndent()
    }

    override fun changeValue(value: String) {
        this.value = value
        notifyChange(value)
    }
}
