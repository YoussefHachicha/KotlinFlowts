package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
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
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.NumberComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.Mode

@Composable
internal fun KfNumberComponent(
    editor: NumberComponentEditor,
    mode: Mode,
    onSignal: (Signal<Double>) -> Unit,
) = Column(modifier = Modifier.fillMaxWidth()) {
    KfNumberComponentImpl(editor, mode, onSignal)
}

@Composable
internal fun ColumnScope.KfNumberComponent(
    editor: NumberComponentEditor,
    mode: Mode,
    onSignal: (Signal<Double>) -> Unit,
) = Column(modifier = Modifier.fillMaxWidth()) {
    KfNumberComponentImpl(editor, mode, onSignal)
}


@Composable
internal fun RowScope.KfNumberComponent(
    editor: NumberComponentEditor,
    mode: Mode,
    onSignal: (Signal<Double>) -> Unit,
) = Column(modifier = Modifier.weight(1f)) {
    KfNumberComponentImpl(editor, mode, onSignal)
}

@Composable
private fun KfNumberComponentImpl(
    editor: NumberComponentEditor,
    mode: Mode,
    onSignal: (Signal<Double>) -> Unit,
) {
    val component = remember(editor) { editor.comp }
    var value by remember { mutableStateOf(component.value?.toString() ?: "") }
    val focus = remember(onSignal) {
        FocusManager(onSignal) {
            editor.value = value.toTolerableNumber() ?: 0.0
        }
    }

    KfTitle(component, modifier = Modifier.testTag("${component.id}-title"))
    OutlinedTextField(
        value = value,
        onValueChange = {
            val v = it.toTolerableNumber() ?: 0.0
            value = v.toString()
            onSignal(Signal.Change(v))
        },
        readOnly = component.disabled || mode == Mode.readonly,
        modifier = Modifier.testTag("${component.id}-body").fillMaxWidth()
            .onFocusChanged(focus.handler),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

}

private fun String.toTolerableNumber() = filter {
    it in '0'..'9' || it == '.'
}.removeSuffix(".0").toDoubleOrNull()