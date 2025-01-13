package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextFieldAreaComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.Mode

@Composable
internal fun KfTextFieldArea(
    modifier: Modifier = Modifier,
    editor: TextFieldAreaComponentEditor,
    mode: Mode,
    onSignal: (Signal<String>) -> Unit
) = Column(modifier) {
    KfTextAreaImpl(editor, mode, onSignal)
}

@Composable
internal fun ColumnScope.KfTextFieldArea(
    modifier: Modifier = Modifier,
    editor: TextFieldAreaComponentEditor,
    mode: Mode,
    onSignal: (Signal<String>) -> Unit
) = Column(modifier) {
    KfTextAreaImpl(editor, mode, onSignal)
}

@Composable
internal fun RowScope.KfTextFieldArea(
    modifier: Modifier = Modifier,
    editor: TextFieldAreaComponentEditor,
    mode: Mode,
    onSignal: (Signal<String>) -> Unit
) = Column(modifier.weight(1f)) {
    KfTextAreaImpl(editor, mode, onSignal)
}

@Composable
internal fun KfTextAreaImpl(
    editor: TextFieldAreaComponentEditor,
    mode: Mode,
    onSignal: (Signal<String>) -> Unit
) {
    val component = remember(editor) { editor.comp }

    if (!editor.disableTitle)
        Text(editor.title, modifier = Modifier
            .padding(bottom = 4.dp)
            .testTag("${component.id}-title")
        )

    val focus = remember(onSignal) { FocusManager(onSignal) { editor.value = editor.value } }

    OutlinedTextField(
        value = editor.value ?: "",
        onValueChange = {
            editor.value = it
            onSignal(Signal.Change(it))
        },
        readOnly = editor.disabled || mode == Mode.readonly,
        minLines = 5,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = editor.borderColor,
            unfocusedBorderColor = editor.borderColor,
        ),
        modifier = Modifier.testTag("${component.id}-body")
            .fillMaxWidth()
            .onFocusChanged(focus.handler)
    )
}