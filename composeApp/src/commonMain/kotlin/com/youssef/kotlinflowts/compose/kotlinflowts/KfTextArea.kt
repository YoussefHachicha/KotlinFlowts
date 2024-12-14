package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextAreaComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.Mode

@Composable
internal fun KfTextArea(
    modifier: Modifier = Modifier,
    editor: TextAreaComponentEditor,
    mode: Mode,
    onSignal: (Signal<String>) -> Unit
) = Column(modifier) {
    KfTextAreaImpl(editor, mode, onSignal)
}

@Composable
internal fun ColumnScope.KfTextArea(
    modifier: Modifier = Modifier,
    editor: TextAreaComponentEditor,
    mode: Mode,
    onSignal: (Signal<String>) -> Unit
) = Column(modifier) {
    KfTextAreaImpl(editor, mode, onSignal)
}

@Composable
internal fun RowScope.KfTextArea(
    modifier: Modifier = Modifier,
    editor: TextAreaComponentEditor,
    mode: Mode,
    onSignal: (Signal<String>) -> Unit
) = Column(modifier.weight(1f)) {
    KfTextAreaImpl(editor, mode, onSignal)
}

@Composable
internal fun KfTextAreaImpl(
    editor: TextAreaComponentEditor,
    mode: Mode,
    onSignal: (Signal<String>) -> Unit
) {
    val component = remember(editor) { editor.comp }
    var value by remember { mutableStateOf(component.value ?: "") }
    val focus = remember(onSignal) { FocusManager(onSignal) { editor.value = value } }

    Text(editor.title, modifier = Modifier.testTag("${component.id}-title"))
    Spacer(modifier = Modifier.height(4.dp))
    OutlinedTextField(
        value = value,
        onValueChange = {
            value = it
            onSignal(Signal.Change(it))
        },
        readOnly = component.disabled || mode == Mode.readonly,
        minLines = 5,
        modifier = Modifier.testTag("${component.id}-body")
            .fillMaxWidth()
            .onFocusChanged(focus.handler)
    )
}