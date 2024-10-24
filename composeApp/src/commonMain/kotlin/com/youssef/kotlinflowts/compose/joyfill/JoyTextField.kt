package com.youssef.kotlinflowts.compose.joyfill

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.joyfill.editors.TextFieldEditor
import com.youssef.kotlinflowts.manager.joyfill.Mode

@Composable
internal fun JoyTextField(
    editor: TextFieldEditor,
    mode: Mode,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier = Modifier.testTag(editor.field.id).fillMaxWidth()) {
    val field = remember(editor) { editor.field }
    JoyTitle(field.title, modifier = Modifier.testTag("${field.id}-title"))
    Spacer(modifier = Modifier.height(2.dp))
    var value by remember(editor) { mutableStateOf(editor.field.value) }
    val focus = remember(onSignal) { FocusManager(onSignal) { editor.value = value } }

    RawTextField(
        value = value,
        borders = true,
        maxLines = 1,
        readonly = field.disabled || mode == Mode.readonly,
        modifier = Modifier.testTag("${field.id}-body"),
        onChange = {
            value = it
            onSignal(Signal.Change(value))
        },
        onFocusChanged = focus.handler
    )
    Spacer(modifier = Modifier.height(8.dp))
}
