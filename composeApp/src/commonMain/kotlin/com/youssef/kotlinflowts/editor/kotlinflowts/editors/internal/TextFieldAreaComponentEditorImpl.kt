package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextFieldAreaComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextFieldAreaComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class TextFieldAreaComponentEditorImpl(
    app: App,
    override val comp: TextFieldAreaComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<String,TextFieldAreaComponent>(app,comp,onChange), TextFieldAreaComponentEditor {
    override fun generateCode(): String {
        return """
            OutlinedTextField(
                value = "${value.orEmpty()}",
                onValueChange = { },
                readOnly = ${comp.disabled},
                minLines = 5,
                colors = TextFieldDefaults.colors(),
                modifier = Modifier.fillMaxWidth()
            )
        """.trimIndent()
    }

    override fun changeValue(value: String) {
        this.value = value
        notifyChange(value)
    }
}