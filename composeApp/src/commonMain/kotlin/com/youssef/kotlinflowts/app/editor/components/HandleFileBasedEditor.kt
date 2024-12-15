package com.youssef.kotlinflowts.app.editor.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.FileBasedComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.FileComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ImageComponentEditor

@Composable
internal fun HandleFileBasedEditor(compEditor: FileBasedComponentEditor) {
    when (compEditor) {
        is ImageComponentEditor -> {
            var url by remember(
                compEditor.fileValue.firstOrNull()?.url.orEmpty()
            ) { mutableStateOf(compEditor.fileValue.firstOrNull()?.url.orEmpty()) }

            OutlinedTextField(
                value = url,
                onValueChange = { url = it },
                label = { Text("Url") }
            )
            TextButton(
                onClick = {
                    if (compEditor.fileValue.isEmpty()) {
                        println("adding sss")
                        compEditor.add(url)
                    } else {
                        compEditor.clear()
                        compEditor.add(url)
                    }
                }
            ) {
                Text("Load Image")
            }
        }
        is FileComponentEditor  -> {} // No UI for file components
    }
}