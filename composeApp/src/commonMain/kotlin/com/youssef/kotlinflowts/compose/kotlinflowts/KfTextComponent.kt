package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
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
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.Mode
import com.youssef.kotlinflowts.utils.hoverSelect

@Composable
internal fun KfTextComponent(
    modifier: Modifier = Modifier,
    editor: TextComponentEditor,
    mode: Mode,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier = modifier) {
    KfTextComponentImpl(editor, mode, onSignal)
}


@Composable
internal fun ColumnScope.KfTextComponent(
    modifier: Modifier = Modifier,
    editor: TextComponentEditor,
    mode: Mode,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier) {
    KfTextComponentImpl(editor, mode, onSignal)
}

@Composable
internal fun RowScope.KfTextComponent(
    modifier: Modifier = Modifier,
    editor: TextComponentEditor,
    mode: Mode,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier.weight(1f)) {
    KfTextComponentImpl(editor, mode, onSignal)
}

@Composable
private fun KfTextComponentImpl(
    editor: TextComponentEditor,
    mode: Mode,
    onSignal: (Signal<String?>) -> Unit,
) {
    val component = remember(editor) { editor.comp }
    KfTitle(editor.title, modifier = Modifier.testTag("${component.id}-title"))
    Spacer(modifier = Modifier.height(2.dp))

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

