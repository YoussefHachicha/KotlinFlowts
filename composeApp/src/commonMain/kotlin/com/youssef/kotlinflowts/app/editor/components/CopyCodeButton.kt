package com.youssef.kotlinflowts.app.editor.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.buildAnnotatedString
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor

@Composable
internal fun CopyCodeButton(
    compEditor: ComponentEditor,
) {
    val clipboardManager = LocalClipboardManager.current
    TextButton(
        onClick = {
            clipboardManager.setText(
                annotatedString = buildAnnotatedString {
                    append(text = compEditor.generateCode())
                }
            )
        },
    ) {
        Text("Copy Code")
    }
}