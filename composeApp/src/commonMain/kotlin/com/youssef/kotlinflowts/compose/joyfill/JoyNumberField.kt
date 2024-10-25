package com.youssef.kotlinflowts.compose.joyfill

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import com.youssef.kotlinflowts.editor.joyfill.editors.NumberComponentEditor
import com.youssef.kotlinflowts.manager.joyfill.Mode

@Composable
internal fun JoyNumberField(
    editor: NumberComponentEditor,
    mode: Mode,
    onSignal: (Signal<Double>) -> Unit,
) {
    val field = remember(editor) { editor.component }
    var value by remember { mutableStateOf(field.value?.toString() ?: "") }
    val focus = remember(onSignal) { FocusManager(onSignal) { editor.value = value.toTolerableNumber() ?: 0.0 } }
    Column(modifier = Modifier.fillMaxWidth()) {
        JoyTitle(field, modifier = Modifier.testTag("${field.id}-title"))
        OutlinedTextField(
            value = value,
            onValueChange = {
                val v = it.toTolerableNumber() ?: 0.0
                value = v.toString()
                onSignal(Signal.Change(v))
            },
            readOnly = field.disabled || mode == Mode.readonly,
            modifier = Modifier.testTag("${field.id}-body").fillMaxWidth().onFocusChanged(focus.handler),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

private fun String.toTolerableNumber() = filter {
    it in '0'..'9' || it == '.'
}.removeSuffix(".0").toDoubleOrNull()