package com.youssef.kotlinflowts.app.editor

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.AppEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextComponentEditor
import com.youssef.kotlinflowts.models.kotlinflowts.Screen

@Composable
fun EditorSample(
    editor: AppEditor,
    currentScreen: Screen?,

) {
    println("currentScreen: $currentScreen")
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
    ) {
        item {
            editor.selectedEditorComponent?.let { compEditor ->
                when(compEditor) {
                    is TextComponentEditor -> {
                        OutlinedTextField(
                            value = compEditor.comp.title,
                            onValueChange = { compEditor.changeTitle(it) },
                            label = { "Title" }
                        )
                    }
                    else -> {

                    }
                }
            }
        }
    }
}