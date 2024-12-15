package com.youssef.kotlinflowts.app.editor.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.youssef.kotlinflowts.compose.kotlinflowts.toTolerableNumber
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DateComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.NumberComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.SignatureComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextAreaComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ValueBasedComponentEditor

@Composable
internal fun HandleValueBasedEditor(compEditor: ValueBasedComponentEditor<*>) {
    when (compEditor) {
        is DateComponentEditor -> {} // No UI for date components
        else                   -> {
            OutlinedTextField(
                value = (compEditor.value ?: "").toString(),
                onValueChange = {
                    when (compEditor) {
                        is NumberComponentEditor   ->
                            compEditor.changeValue(it.toTolerableNumber() ?: 0.0)
                        is TextAreaComponentEditor  -> compEditor.changeValue(it)
                        is TextComponentEditor      -> compEditor.changeValue(it)
                        is SignatureComponentEditor -> compEditor.changeValue(it)
                    }
                },
                label = { Text("Value") }
            )
        }
    }
}