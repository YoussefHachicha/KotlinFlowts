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
import com.youssef.kotlinflowts.editor.joyfill.editors.TextComponentEditor
import com.youssef.kotlinflowts.manager.joyfill.Mode

@Composable
internal fun JoyTextComponent(
    editor: TextComponentEditor,
    mode: Mode,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier = Modifier.testTag(editor.component.id).fillMaxWidth()) {
    val component = remember(editor) { editor.component }
    JoyTitle(component.title, modifier = Modifier.testTag("${component.id}-title"))
    Spacer(modifier = Modifier.height(2.dp))
    var value by remember(editor) { mutableStateOf(editor.component.value) }
    val focus = remember(onSignal) { FocusManager(onSignal) { editor.value = value } }

    RawTextComponent(
        value = value,
        borders = true,
        maxLines = 1,
        readonly = component.disabled || mode == Mode.readonly,
        modifier = Modifier.testTag("${component.id}-body"),
        onChange = {
            value = it
            onSignal(Signal.Change(value))
        },
        onFocusChanged = focus.handler
    )
    Spacer(modifier = Modifier.height(8.dp))
}
