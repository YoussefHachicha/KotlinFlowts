package com.youssef.kotlinflowts.app.editor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.AppEditor
import com.youssef.kotlinflowts.utils.colorPicker.ColorConfig

@Composable
fun EditorSample(
    editor: AppEditor,
) {
    val isCollapsed = remember { true }

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
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Border Color:")
                    ColorConfig(
                        selectedColor = compEditor.borderColor,
                        onColorChanged = {
                            compEditor.changeBorderColor(it)
                            editor.changeBorderColor(it, compEditor.id)
                        },
                        isCollapsed = isCollapsed,
                        isRow = isCollapsed
                    )
                }

            }
        }
    }
}