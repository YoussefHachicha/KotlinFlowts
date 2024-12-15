package com.youssef.kotlinflowts.app.editor.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.compose.kotlinflowts.KfOption
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.MultiSelectComponentEditor

@Composable
internal fun HandleMultiSelectEditor(compEditor: MultiSelectComponentEditor) {
    var newOption by remember { mutableStateOf("") }

    KfOption(
        label = "Multiple options",
        selected = compEditor.multiple,
        onClick = { compEditor.changeMultiple() }
    )

    OutlinedTextField(
        value = newOption,
        onValueChange = { newOption = it },
        label = { Text("Option") }
    )

    TextButton(
        onClick = {
            compEditor.addOption(newOption)
            newOption = ""
        }
    ) {
        Text("Add Option")
    }

}