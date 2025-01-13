package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextFieldComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class TextFieldComponentEditorImpl(
    app: App,
    override val comp: TextFieldComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<String,TextFieldComponent>(app,comp,onChange), TextFieldComponentEditor {
    override fun generateCode(): String {
        return """
            OutlinedTextField(
                value = "${value.orEmpty()}",
                onValueChange = { },
                readOnly = ${comp.disabled},
                singleLine = true,
                maxLines = 1,
                colors = OutlinedTextFieldDefaults.colors(),
                modifier = Modifier.fillMaxWidth()
            )
        """.trimIndent()
    }

    override fun changeValue(value: String) {
        this.value = value
        notifyChange(value)
    }
}