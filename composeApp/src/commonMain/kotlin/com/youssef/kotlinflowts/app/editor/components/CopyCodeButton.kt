package com.youssef.kotlinflowts.app.editor.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.buildAnnotatedString
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor

@Composable
internal fun CopyCodeButton(
    compEditor: ComponentEditor,
) {
    val clipboardManager = LocalClipboardManager.current
    val title by remember(compEditor) {
        mutableStateOf(
            compEditor.title.ifEmpty { compEditor.comp.title }.replaceFirstChar { it.uppercase() }.replace(" ", "")
        )
    }

    TextButton(
        onClick = {
            clipboardManager.setText(
                annotatedString = buildAnnotatedString {
                    append("@Composable")
                    append("\n")
                    append("fun $title() {")
                    append("\n")
                    append(text = compEditor.generateCode())
                    append("\n")
                    append("}")
                }
            )
        },
    ) {
        Text("Copy Code")
    }
}

