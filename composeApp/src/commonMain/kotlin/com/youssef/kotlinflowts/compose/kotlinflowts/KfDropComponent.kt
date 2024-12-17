package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DropdownComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.Mode
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2

@Composable
internal fun KfDropComponent(
    modifier: Modifier = Modifier,
    editor: DropdownComponentEditor,
    mode: Mode,
    multiple: Boolean = editor.multiple,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier) {
    KfDropComponentImpl(editor, mode, multiple, onSignal)
}

@Composable
internal fun ColumnScope.KfDropComponent(
    modifier: Modifier = Modifier,
    editor: DropdownComponentEditor,
    mode: Mode,
    multiple: Boolean = editor.multiple,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier) {
    KfDropComponentImpl(editor, mode, multiple, onSignal)
}

@Composable
internal fun RowScope.KfDropComponent(
    modifier: Modifier = Modifier,
    editor: DropdownComponentEditor,
    mode: Mode,
    multiple: Boolean = editor.multiple,
    onSignal: (Signal<String?>) -> Unit,
) = Column(modifier.weight(1f)) {
    KfDropComponentImpl(editor, mode, multiple, onSignal)
}

@Composable
private fun KfDropComponentImpl(
    editor: DropdownComponentEditor,
    mode: Mode,
    multiple: Boolean,
    onSignal: (Signal<String?>) -> Unit,
) {
    val component = remember(editor) { editor.comp }

    val options by derivedStateOf { editor.options.toList() }

    var selected by remember(editor) { mutableStateOf(editor.selected()?.value) }

    val focus = remember(onSignal) { FocusManager(onSignal) { editor.select(selected) } }

    if (!editor.disableTitle)
        KfTitle(editor, modifier = Modifier.testTag("${component.id}-title"))

    RawDropComponent(
        options = options,
        value = selected?.let { listOf(it) } ?: emptyList(),
        readonly = editor.disabled || mode == Mode.readonly,
        multiple = multiple,
        borders = true,
        borderColor = editor.borderColor,
        onChange = {
            selected = it.firstOrNull()?.value
            editor.select(it.lastOrNull())
            onSignal(Signal.Change(selected))
        },
        modifier = Modifier
            .testTag("${component.id}-body")
            .onFocusChanged(focus.handler)
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RawDropComponent(
    options: List<Option2>,
    value: List<String>,
    readonly: Boolean,
    borderColor: Color = Color.Transparent,
    multiple: Boolean,
    borders: Boolean,
    onChange: (List<Option2>) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val values = remember(value, options) {
        val v = value.mapNotNull { vl ->
            options.find { vl == it.value }
        }
        mutableStateListOf(*v.toTypedArray())
    }

    ExposedDropdownMenuBox(
        expanded = !readonly && expanded,
        onExpandedChange = { expanded = !readonly && it },
        modifier = modifier.fillMaxWidth()
    ) {

        OutlinedTextField(
            value = values.joinToString(", ") { it.value },
            maxLines = 4,
            readOnly = true,
            onValueChange = {},
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            colors = if (borders) {
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                )
            } else {
                OutlinedTextFieldDefaults.colors()
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
                .clickable { expanded = !readonly }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            for (option in options) DropdownMenuItem(
                modifier = Modifier.semantics { contentDescription = option.value },
                text = { Text(option.value) },
                leadingIcon = {
                    if (option in values && multiple) {
                        Icon(Icons.Default.Check, "selected")
                    }
                },
                onClick = {
                    if (!multiple) values.clear()
                    val func = if (option in values) values::remove else values::add
                    func(option)
                    onChange(values)
                    if (!multiple) {
                        expanded = false
                    }
                },
            )
        }
    }
}