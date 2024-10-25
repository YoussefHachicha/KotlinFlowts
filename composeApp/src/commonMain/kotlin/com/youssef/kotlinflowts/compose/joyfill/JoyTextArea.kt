package com.youssef.kotlinflowts.compose.joyfill

import androidx.compose.foundation.layout.Column
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
import com.youssef.kotlinflowts.editor.joyfill.editors.TextAreaComponentEditor
import com.youssef.kotlinflowts.manager.joyfill.Mode

@Composable
internal fun JoyTextArea(
    editor: TextAreaComponentEditor,
    mode: Mode,
    onSignal: (Signal<String>) -> Unit
) {
    val field = remember(editor) { editor.component }
    var value by remember { mutableStateOf(field.value ?: "") }
    val focus = remember(onSignal) { FocusManager(onSignal) { editor.value = value } }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(field.title, modifier = Modifier.testTag("${field.id}-title"))
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = {
                value = it
                onSignal(Signal.Change(it))
            },
            readOnly = field.disabled || mode == Mode.readonly,
            minLines = 5,
            modifier = Modifier.testTag("${field.id}-body")
                .fillMaxWidth()
                .onFocusChanged(focus.handler)
        )
    }
}