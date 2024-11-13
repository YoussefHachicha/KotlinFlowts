package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
internal fun KfDisplayComponent(
    editor: TextComponentEditor,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier = Modifier.testTag(editor.comp.id).fillMaxWidth()) {
    KfDisplayTextComponentImpl(editor, onSignal)
}

@Composable
internal fun ColumnScope.KfDisplayTextComponent(
    editor: TextComponentEditor,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier = Modifier.testTag(editor.comp.id).fillMaxWidth()) {
    KfDisplayTextComponentImpl(editor, onSignal)
}

@Composable
internal fun RowScope.KfDisplayTextComponent(
    editor: TextComponentEditor,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier = Modifier.weight(1f).testTag(editor.comp.id)) {
    KfDisplayTextComponentImpl(editor, onSignal)
}

@Composable
private fun KfDisplayTextComponentImpl(
    editor: TextComponentEditor,
    onSignal: (Signal<String?>) -> Unit,
) {
    val component = remember(editor) { editor.comp }
    KfTitle(component.title, modifier = Modifier.testTag("${component.id}-title"))
    Spacer(modifier = Modifier.height(2.dp))
    val value by remember(editor) {
        onSignal(Signal.Change(editor.comp.value))
        mutableStateOf(editor.comp.value ?: "text")
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


