package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.NumberFieldComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.NumberFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class NumberFieldComponentEditorImpl(
    app: App,
    override val comp: NumberFieldComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<Double, NumberFieldComponent>(app, comp, onChange), NumberFieldComponentEditor {
    override fun generateCode(): String {
        return """
            OutlinedTextField(
                value = "${value ?: ""}",
                onValueChange = { },
                readOnly = ${comp.disabled},
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        """.trimIndent()
    }

    override fun changeValue(value: Double) {
        this.value = value
        notifyChange(value)
    }
}