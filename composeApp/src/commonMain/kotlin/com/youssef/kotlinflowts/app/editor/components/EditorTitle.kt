package com.youssef.kotlinflowts.app.editor.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor

@Composable
internal fun EditorTitle(compEditor: ComponentEditor) {
    OutlinedTextField(
        value = compEditor.title,
        onValueChange = {
            compEditor.changeTitle(it)
        },
        enabled = !compEditor.disableTitle,
        label = { Text("Title") }
    )
}
