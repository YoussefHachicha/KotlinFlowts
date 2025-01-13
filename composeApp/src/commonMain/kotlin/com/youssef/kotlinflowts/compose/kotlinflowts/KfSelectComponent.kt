package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.MultiSelectComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.Mode

@Composable
internal fun KfSelectComponent(
    modifier: Modifier = Modifier,
    editor: MultiSelectComponentEditor,
    mode: Mode,
    multiple: Boolean = editor.multiple,
    onSignal: (Signal<List<String>>) -> Unit
) = Column(modifier) {
    KfSelectComponentImpl(editor, mode, multiple, onSignal)
}

@Composable
internal fun ColumnScope.KfSelectComponent(
    modifier: Modifier = Modifier,
    editor: MultiSelectComponentEditor,
    mode: Mode,
    multiple: Boolean = editor.multiple,
    onSignal: (Signal<List<String>>) -> Unit
) = Column(modifier) {
    KfSelectComponentImpl(editor, mode, multiple, onSignal)
}

@Composable
internal fun RowScope.KfSelectComponent(
    modifier: Modifier = Modifier,
    editor: MultiSelectComponentEditor,
    mode: Mode,
    multiple: Boolean = editor.multiple,
    onSignal: (Signal<List<String>>) -> Unit
) = Column(modifier.weight(1f)) {
    KfSelectComponentImpl(editor, mode, multiple, onSignal)
}


@Composable
private fun KfSelectComponentImpl(
    editor: MultiSelectComponentEditor,
    mode: Mode,
    multiple: Boolean,
    onSignal: (Signal<List<String>>) -> Unit
) {
    val component = remember(editor) { editor.comp }
    val options by derivedStateOf { editor.options.toList() }

    val values = remember(editor) {
        mutableStateListOf(*editor.getSelected().map { it.id }.toTypedArray())
    }

    val focus = remember(onSignal) { FocusManager(onSignal) { } }
    if (!editor.disableTitle)
        KfTitle(editor.title, modifier = Modifier.testTag("${component.id}-title"))

    Surface(
        color = MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.2f),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth().testTag("${component.id}-body")
            .onFocusChanged(focus.handler)
    ) {
        Column(modifier = Modifier.padding(vertical = 7.dp)) {
            val size = options.size
            options.forEachIndexed { index, option ->
                KfOption(
                    label = option.value,
                    selected = option.id in values,
                    modifier = Modifier.semantics { contentDescription = option.value },
                    onClick = {
                        if (editor.disabled || mode == Mode.readonly) return@KfOption
                        if (!multiple) values.clear()
                        if (option.id in values) {
                            editor.unselect(option)
                            values.remove(option.id)
                        } else {
                            editor.select(option)
                            values.add(option.id)
                        }
                        onSignal(Signal.Change(values.toList()))
                    }
                )
                if (index != size - 1) Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(LocalContentColor.current.copy(0.1f))
                )
            }
        }
    }
}

@Composable
fun KfOption(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(horizontal = 14.dp, vertical = 7.dp)
) {
    val prefix = remember(selected) { (if (selected) "" else "un") + "selected" }
    Checkbox(
        selected,
        null,
        modifier = Modifier.semantics { contentDescription = "$prefix $label checkbox" })
    Spacer(modifier = Modifier.width(7.dp))
    Text(label, modifier = Modifier.semantics { contentDescription = "$prefix $label value" })
}