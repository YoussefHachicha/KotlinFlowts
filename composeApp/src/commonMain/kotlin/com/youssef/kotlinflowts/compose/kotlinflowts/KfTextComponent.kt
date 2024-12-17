package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextComponentEditor

@Composable
internal fun KfTextComponent(
    modifier: Modifier = Modifier,
    editor: TextComponentEditor,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier = modifier.testTag(editor.comp.id).fillMaxWidth()) {
    KfTextComponentImpl(editor, onSignal, modifier)
}

@Composable
internal fun ColumnScope.KfTextComponent(
    modifier: Modifier = Modifier,
    editor: TextComponentEditor,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier = modifier.testTag(editor.comp.id).fillMaxWidth()) {
    KfTextComponentImpl(editor, onSignal, modifier)
}

@Composable
internal fun RowScope.KfTextComponent(
    modifier: Modifier = Modifier,
    editor: TextComponentEditor,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier = modifier.weight(1f).testTag(editor.comp.id)) {
    KfTextComponentImpl(editor, onSignal, modifier)
}

@Composable
private fun KfTextComponentImpl(
    editor: TextComponentEditor,
    onSignal: (Signal<String?>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val component = remember(editor) { editor.comp }

    if (!editor.disableTitle)
        KfTitle(editor.title, modifier = Modifier.testTag("${component.id}-title"))

    val value by remember(editor.value) {
        onSignal(Signal.Change(editor.comp.value))
        mutableStateOf(editor.value.orEmpty())
    }

    val focus = remember(onSignal) { FocusManager(onSignal) { editor.value = value } }

    Text(
        text = value,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .testTag("${component.id}-body")
            .onFocusChanged(focus.handler)
    )
}