package com.youssef.kotlinflowts.app.editor.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor

@Composable
internal fun DeleteButton(
    compEditor: ComponentEditor,
    delete: (id: String, builderId: String) -> Unit
) {
    TextButton(
        onClick = {
            delete(compEditor.comp.id, compEditor.comp.builderId)
        },
        colors = ButtonDefaults.textButtonColors(contentColor = Color.Red)
    ) {
        Text("Delete")
    }
}