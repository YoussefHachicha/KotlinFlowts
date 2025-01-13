package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.SignatureComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.SignatureComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class SignatureComponentEditorImpl(
    app: App,
    override val comp: SignatureComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<String, SignatureComponent>(app, comp, onChange), SignatureComponentEditor {
    override fun generateCode(): String {
        return """
            Text("Signature component")
        """.trimIndent()
    }

    override fun changeValue(value: String) {
        this.value = value
        notifyChange(value)
    }
}