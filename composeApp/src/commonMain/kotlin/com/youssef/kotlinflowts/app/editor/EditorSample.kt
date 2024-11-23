package com.youssef.kotlinflowts.app.editor

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.AppEditor

@Composable
fun EditorSample(
    editor: AppEditor,
) {
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
    ) {
        item {
            editor.selectedEditorComponent?.let { compEditor ->
                OutlinedTextField(
                    value = compEditor.title,
                    onValueChange = {
                        compEditor.changeTitle(it)
                        editor.changeTitle(it, compEditor.id)
                    },
                    label = { Text("Title") }
                )
            }
        }
    }
}