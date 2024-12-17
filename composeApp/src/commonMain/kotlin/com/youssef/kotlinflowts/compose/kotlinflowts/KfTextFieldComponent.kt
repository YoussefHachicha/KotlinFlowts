package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextFieldComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.Mode

@Composable
internal fun KfTextFieldComponent(
    modifier: Modifier = Modifier,
    editor: TextFieldComponentEditor,
    mode: Mode,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier = modifier) {
    KfTextComponentImpl(editor, mode, onSignal)
}


@Composable
internal fun ColumnScope.KfTextFieldComponent(
    modifier: Modifier = Modifier,
    editor: TextFieldComponentEditor,
    mode: Mode,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier) {
    KfTextComponentImpl(editor, mode, onSignal)
}

@Composable
internal fun RowScope.KfTextFieldComponent(
    modifier: Modifier = Modifier,
    editor: TextFieldComponentEditor,
    mode: Mode,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier.weight(1f)) {
    KfTextComponentImpl(editor, mode, onSignal)
}

@Composable
private fun KfTextComponentImpl(
    editor: TextFieldComponentEditor,
    mode: Mode,
    onSignal: (Signal<String?>) -> Unit,
) {
    val component = remember(editor) { editor.comp }
    if (!editor.disableTitle)
        KfTitle(editor.title, modifier = Modifier.testTag("${component.id}-title"))

    RawTextComponent(
        value = editor.value ?: "",
        borders = true,
        borderColor = editor.borderColor,
        maxLines = 1,
        readonly = editor.disabled || mode == Mode.readonly,
        modifier = Modifier.fillMaxWidth().testTag("${component.id}-body"),
        onChange = {
            editor.value = it
            onSignal(Signal.Change(it))
        },
    )
}

