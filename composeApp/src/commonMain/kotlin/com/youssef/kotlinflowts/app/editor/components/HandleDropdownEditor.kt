package com.youssef.kotlinflowts.app.editor.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.youssef.kotlinflowts.compose.kotlinflowts.KfOption
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DropdownComponentEditor

@Composable
internal fun HandleDropdownEditor(compEditor: DropdownComponentEditor) {
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
        },
        enabled = !compEditor.disabled
    ) {
        Text("Add Option")
    }

    compEditor.selected?.let {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(it.value)
            TextButton(
                onClick = { compEditor.removeOption(it.id) },
                colors = ButtonDefaults.textButtonColors(contentColor = Color.Red)
            ) {
                Text("Remove")
            }
        }
    }

}
