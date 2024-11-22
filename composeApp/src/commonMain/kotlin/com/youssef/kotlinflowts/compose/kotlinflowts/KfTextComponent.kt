package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.TextComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.Mode
import com.youssef.kotlinflowts.utils.clickableNoIndication
import com.youssef.kotlinflowts.utils.thenIf

@Composable
internal fun KfTextComponent(
    editor: TextComponentEditor,
    mode: Mode,
    onSignal: (Signal<String?>) -> Unit,
    isSelected: Boolean,
    select: (ComponentEditor) -> Unit,
) = Column(modifier = Modifier
    .testTag(editor.comp.id)
    .fillMaxWidth()
    .clickableNoIndication {
        select(editor)
    }
    .thenIf(isSelected){
        border(
            width = 1.dp,
            color = Color.Blue
        )
    }
) {
    KfTextComponentImpl(editor, mode, onSignal)
}

@Composable
internal fun ColumnScope.KfTextComponent(
    editor: TextComponentEditor,
    mode: Mode,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier = Modifier.testTag(editor.comp.id).fillMaxWidth()) {
    KfTextComponentImpl(editor, mode, onSignal)
}

@Composable
internal fun RowScope.KfTextComponent(
    editor: TextComponentEditor,
    mode: Mode,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier = Modifier.weight(1f).testTag(editor.comp.id)) {
    KfTextComponentImpl(editor, mode, onSignal)
}

@Composable
private fun KfTextComponentImpl(
    editor: TextComponentEditor,
    mode: Mode,
    onSignal: (Signal<String?>) -> Unit,
) {
    val component = remember(editor) { editor.comp }
    KfTitle(component.title, modifier = Modifier.testTag("${component.id}-title"))
    Spacer(modifier = Modifier.height(2.dp))
    var value by remember(editor) { mutableStateOf(editor.comp.value) }
    val focus = remember(onSignal) { FocusManager(onSignal) { editor.value = value } }

    RawTextComponent(
        value = value,
        borders = true,
        maxLines = 1,
        readonly = component.disabled || mode == Mode.readonly,
        modifier = Modifier.fillMaxWidth().testTag("${component.id}-body"),
        onChange = {
            value = it
            onSignal(Signal.Change(value))
        },
        onFocusChanged = focus.handler
    )
}


