package com.youssef.kotlinflowts.app.editor

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.app.editor.components.BorderColorConfig
import com.youssef.kotlinflowts.app.editor.components.DeleteButton
import com.youssef.kotlinflowts.app.editor.components.EditorTitle
import com.youssef.kotlinflowts.app.editor.components.HandleDropdownEditor
import com.youssef.kotlinflowts.app.editor.components.HandleFileBasedEditor
import com.youssef.kotlinflowts.app.editor.components.HandleValueBasedEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.AppEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DropdownEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.FileBasedComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ValueBasedComponentEditor

@Composable
fun EditorSample(
    editor: AppEditor,
    delete: (id: String, builderId: String) -> Unit
) {
    val isCollapsed = remember { true }

    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        item {
            editor.selectedEditorComponent?.let { compEditor ->
                EditorTitle(compEditor)

                when (compEditor) {
                    is ValueBasedComponentEditor<*> -> HandleValueBasedEditor(compEditor)
                    is FileBasedComponentEditor -> HandleFileBasedEditor(compEditor)
                    is DropdownEditor -> HandleDropdownEditor(compEditor)
                    else -> {}
                }

                BorderColorConfig(compEditor, isCollapsed)

                DeleteButton(compEditor, delete)
            }
        }
    }
}