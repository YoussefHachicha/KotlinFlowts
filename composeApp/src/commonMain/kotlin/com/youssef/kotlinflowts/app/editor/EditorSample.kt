package com.youssef.kotlinflowts.app.editor

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.app.editor.components.BorderColorConfig
import com.youssef.kotlinflowts.app.editor.components.CopyCodeButton
import com.youssef.kotlinflowts.app.editor.components.DeleteButton
import com.youssef.kotlinflowts.app.editor.components.EditorTitle
import com.youssef.kotlinflowts.app.editor.components.HandleDropdownEditor
import com.youssef.kotlinflowts.app.editor.components.HandleFileBasedEditor
import com.youssef.kotlinflowts.app.editor.components.HandleMultiSelectEditor
import com.youssef.kotlinflowts.app.editor.components.HandleValueBasedEditor
import com.youssef.kotlinflowts.compose.kotlinflowts.KfOption
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.AppEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DropdownComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.FileBasedComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.MultiSelectComponentEditor
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

                KfOption(
                    label = "Disable Title",
                    selected = compEditor.disabled,
                    onClick = { compEditor.changeDisableTitle() }
                )

                BorderColorConfig(compEditor, isCollapsed)

                KfOption(
                    label = "Disable",
                    selected = compEditor.disabled,
                    onClick = { compEditor.changeDisabled() }
                )

                CopyCodeButton(compEditor)

                DeleteButton(compEditor, delete)

                Spacer(modifier = Modifier.padding(8.dp))

                HorizontalDivider()

                Spacer(modifier = Modifier.padding(8.dp))

                when (compEditor) {
                    is ValueBasedComponentEditor<*> -> HandleValueBasedEditor(compEditor)
                    is FileBasedComponentEditor -> HandleFileBasedEditor(compEditor)
                    is DropdownComponentEditor -> HandleDropdownEditor(compEditor)
                    is MultiSelectComponentEditor -> HandleMultiSelectEditor(compEditor)
                    else -> {}
                }
            }
        }
    }
}
