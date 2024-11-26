package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class TextComponentEditorImpl(
    app: App,
    override val comp: TextComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<String,TextComponent>(app,comp,onChange), TextComponentEditor{
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
}
