package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
    modifier: Modifier = Modifier,
    editor: NumberComponentEditor,
    mode: Mode,
    onSignal: (Signal<Double>) -> Unit,
) = Column(modifier) {
    KfNumberComponentImpl(editor, mode, onSignal)
}

@Composable
internal fun ColumnScope.KfNumberComponent(
    modifier: Modifier = Modifier,
    editor: NumberComponentEditor,
    mode: Mode,
    onSignal: (Signal<Double>) -> Unit,
) = Column(modifier) {
    KfNumberComponentImpl(editor, mode, onSignal)
}


@Composable
internal fun RowScope.KfNumberComponent(
    modifier: Modifier = Modifier,
    editor: NumberComponentEditor,
    mode: Mode,
    onSignal: (Signal<Double>) -> Unit,
) = Column(modifier.weight(1f)) {
    KfNumberComponentImpl(editor, mode, onSignal)
}

@Composable
private fun KfNumberComponentImpl(
    editor: NumberComponentEditor,
    mode: Mode,
    onSignal: (Signal<Double>) -> Unit,
) {
    val component = remember(editor) { editor.comp }
    KfTitle(editor, modifier = Modifier.testTag("${component.id}-title"))
    OutlinedTextField(
        value = editor.value?.toString() ?: "",
        onValueChange = {
            val v = it.toTolerableNumber() ?: 0.0
            editor.value = v
            onSignal(Signal.Change(v))
        },
        readOnly = component.disabled || mode == Mode.readonly,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = editor.borderColor,
            unfocusedBorderColor = editor.borderColor,
        ),
        modifier = Modifier.testTag("${component.id}-body").fillMaxWidth()
    )

}

fun String.toTolerableNumber() = filter {
    it in '0'..'9' || it == '.'
}.removeSuffix(".0").toDoubleOrNull()