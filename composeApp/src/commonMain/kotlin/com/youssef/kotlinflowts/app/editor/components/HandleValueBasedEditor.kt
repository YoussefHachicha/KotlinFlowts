package com.youssef.kotlinflowts.app.editor.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.youssef.kotlinflowts.compose.kotlinflowts.toTolerableNumber
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DateFieldComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.NumberFieldComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.SignatureComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextFieldAreaComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextFieldComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ValueBasedComponentEditor

@Composable
internal fun HandleValueBasedEditor(compEditor: ValueBasedComponentEditor<*>) {
    when (compEditor) {
        is DateFieldComponentEditor -> {} // No UI for date components
        else                        -> {
            OutlinedTextField(
                value = (compEditor.value ?: "").toString(),
                onValueChange = {
                    when (compEditor) {
                        is TextComponentEditor      -> compEditor.changeValue(it)
                        is TextFieldComponentEditor -> compEditor.changeValue(it)
                        is TextFieldAreaComponentEditor -> compEditor.changeValue(it)
                        is NumberFieldComponentEditor   ->
                            compEditor.changeValue(it.toTolerableNumber() ?: 0.0)
                        is SignatureComponentEditor -> compEditor.changeValue(it)
                    }
                },
                label = { Text("Value") }
            )
        }
    }
}