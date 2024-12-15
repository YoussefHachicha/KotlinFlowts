package com.youssef.kotlinflowts.app.editor.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DropdownEditor

@Composable
internal fun HandleDropdownEditor(compEditor: DropdownEditor) {
    var option by remember { mutableStateOf("") }
    OutlinedTextField(
        value = option,
        onValueChange = { option = it },
        label = { Text("Option") }
    )
    TextButton(
        onClick = {
            compEditor.addOption(option)
            option = ""
        }
    ) {
        Text("Add Option")
    }
}
