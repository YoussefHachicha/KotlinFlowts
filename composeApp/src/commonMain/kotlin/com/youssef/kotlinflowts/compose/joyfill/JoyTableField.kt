package com.youssef.kotlinflowts.compose.joyfill

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideIn
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.youssef.kotlinflowts.compose.joyfill.tables.LazyRowArrayTable
import com.youssef.kotlinflowts.compose.joyfill.tables.RowArrayTable
import com.youssef.kotlinflowts.editor.joyfill.editors.BlockComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ChartComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.column.ColumnComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.DateComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.DropdownComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ImageComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.MultiSelectComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.NumberComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.RichTextComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.SignatureComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TableComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TextAreaComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TextComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.table.DropdownCellEditor
import com.youssef.kotlinflowts.editor.joyfill.table.ImageCellEditor
import com.youssef.kotlinflowts.editor.joyfill.table.TextCellEditor
import com.youssef.kotlinflowts.manager.joyfill.ComponentEvent
import com.youssef.kotlinflowts.manager.joyfill.Mode
import com.youssef.kotlinflowts.models.joyfill.Screen
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.components.table.Column
import com.youssef.kotlinflowts.models.joyfill.components.table.DropdownColumn
import com.youssef.kotlinflowts.models.joyfill.components.table.ImageColumn
import com.youssef.kotlinflowts.models.joyfill.components.table.TextColumn
import com.youssef.kotlinflowts.models.joyfill.utils.option
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun JoyTableComponent(
    editor: TableComponentEditor,
    screen: Screen,
    previewRows: Int,
    mode: Mode,
    onUpload: (suspend (ComponentEvent) -> List<String>)?,
) {
    var view by remember { mutableStateOf(UIView.small) }
    val component = remember(editor) { editor.component }
    val allRows = remember { mutableStateListOf(*(editor.component.value).toTypedArray()) }
    val rows by remember(allRows.size) { derivedStateOf { allRows.filter { !it.deleted && it.id in component.rowOrder } } }
    val selectedRow = remember { mutableStateListOf<Int>() }
    val scope = rememberCoroutineScope()

    val density = LocalDensity.current
    val measurer = TextMeasurer(
        defaultFontFamilyResolver = LocalFontFamilyResolver.current,
        defaultDensity = density,
        defaultLayoutDirection = LocalLayoutDirection.current
    )

    Preview(editor, previewRows, totalRows = rows.size, onClick = { view = UIView.large })

    val uploadHandler = if (onUpload != null) {
        suspend {
            val event = ComponentEvent(
                component = editor.component,
                screen = screen
            )
            onUpload(event)
        }
    } else null

    AnimatedVisibility(
        visible = view == UIView.large,
        enter = slideIn { IntOffset.Zero },
        modifier = Modifier.testTag("${editor.component.id}-large")
    ) {
        var subDialog by remember { mutableStateOf(false) }
        Dialog(
            onDismissRequest = { view = UIView.small },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(modifier = Modifier.fillMaxSize(0.95f)) {
                Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        JoyTitle(component, modifier = Modifier.padding(bottom = 4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (selectedRow.size == 1) {
                                val width = with(density) { measurer.measure("More").size.width.toDp() + 40.dp }
                                Box(
                                    modifier = Modifier.width(width).height(40.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    RawDropComponent(
                                        options = listOf("Delete").map { option(it, it, false) },
                                        value = emptyList(),
                                        readonly = false,
                                        multiple = false,
                                        borders = true,
                                        onChange = {
                                            val first = it.firstOrNull()
                                            val index = selectedRow.firstOrNull()
                                            if (first?.value == "Delete" && index != null) {
                                                editor.rows.deleteAt(index)
                                                allRows.removeAt(index)
                                                selectedRow.remove(index)
                                            }
                                        }
                                    )
                                    Text("More")
                                }
                            }
                            Icon(
                                Icons.Filled.Close,
                                "close",
                                modifier = Modifier.testTag("${component.id}-large-close")
                                    .clickable { view = UIView.small })
                        }
                    }
                    val state = rememberLazyListState()

                    LazyRowArrayTable(
                        state = state,
                        cols = component.columns.size,
                        rows = rows.size,
                        key = { component.rowOrder[it] },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.9f)
                    ) { row, col ->
                        val width = if (col < 0) with(density) {
                            measurer.measure("${rows.size}").size.width.toDp() + 70.dp
                        } else colWidth(component.columns[col].type, preview = false)

                        val modifier = Modifier.width(width).padding(horizontal = 8.dp)
                        Box(modifier) {
                            when {
                                col == -1 && row == -1 -> {}
                                col == -1 && row != -1 -> Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(text = "${row + 1}", color = LocalContentColor.current.copy(alpha = 0.6f))
                                    Checkbox(
                                        checked = selectedRow.contains(row),
                                        onCheckedChange = { checked ->
                                            if (checked) selectedRow.add(row) else selectedRow.remove(row)
                                        }
                                    )
                                }

                                col != -1 && row == -1 -> Text(
                                    text = component.columns[col].title,
                                    modifier = modifier
                                )

                                else /* col != -1 && row != -1 */ -> when (val cell = editor.rows.get(row)?.col(col)) {
                                    is TextCellEditor -> RawTextComponent(
                                        value = cell.value ?: "",
                                        readonly = mode == Mode.readonly,
                                        borders = false,
                                        onChange = { cell.value = it },
                                        onFocusChanged = {

                                        }
                                    )

                                    is DropdownCellEditor -> RawDropComponent(
                                        options = cell.options,
                                        value = cell.selected()?.value?.let { listOf(it) } ?: emptyList(),
                                        readonly = false,
                                        borders = false,
                                        multiple = false,
                                        onChange = { selected ->
                                            cell.select(selected.firstOrNull()?.id)
                                        }
                                    )

                                    is ImageCellEditor -> RawImageComponent(
                                        id = "${component.id}-$row:$col",
                                        title = cell.column.title,
                                        readonly = mode == Mode.readonly,
                                        uploaded = cell.value,
                                        onUpload = uploadHandler,
                                        onDialog = {},
                                        onAdded = { cell.add(it) },
                                        onRemoved = { cell.remove(it) },
                                    ) {
                                        UploadLabel(modifier = Modifier.fillMaxWidth(), onClick = it::openModal)
                                    }
                                }
                            }
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Button(
                            shape = RoundedCornerShape(8.dp),
                            onClick = {
                                val row = editor.rows.append()
                                allRows.add(row.row)
                                scope.launch {
                                    delay(100) // wait for the row to be added
                                    state.scrollToItem(allRows.size)
                                }
                            }
                        ) {
                            Icon(Icons.Outlined.Add, "Add row")
                            Text("Add Row")
                        }
                    }
                }
            }

            if (subDialog) Dialog(
                onDismissRequest = { subDialog = false },
                properties = DialogProperties(
                    usePlatformDefaultWidth = false
                )
            ) {
                Surface(modifier = Modifier.fillMaxSize(0.8f)) {
                    RowCapture(
                        title = component.title,
                        columns = component.columns,
                        mode = mode,
                        onUpload = uploadHandler,
                        onClose = { subDialog = false }
                    )
                }
            }
        }
    }
}

private enum class UIView {
    small, large
}

@Composable
private fun RowCapture(
    title: String,
    columns: List<Column>,
    mode: Mode,
    onUpload: (suspend () -> List<String>)?,
    onClose: () -> Unit,
) = Column(modifier = Modifier.fillMaxWidth().padding(8.dp).verticalScroll(rememberScrollState())) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        JoyTitle("$title entry", modifier = Modifier.padding(bottom = 4.dp))
        Icon(Icons.Filled.Close, "close", modifier = Modifier.testTag("${title}-capture-close").clickable { onClose() })
    }

    columns.forEach { column ->
        JoyTitle(column.title, modifier = Modifier.padding(top = 8.dp))
        when (column) {
            is TextColumn -> RawTextComponent(
                value = "",
                readonly = mode == Mode.readonly,
                borders = true,
                onChange = {},
                onFocusChanged = {}
            )

            is DropdownColumn -> RawDropComponent(
                options = column.options,
                value = emptyList(),
                readonly = false,
                borders = true,
                multiple = false,
                onChange = {}
            )

            is ImageColumn -> RawImageComponent(
                id = column.id,
                title = column.title,
                readonly = mode == Mode.readonly,
                onDialog = {},
                uploaded = emptyList(),
                onUpload = onUpload,
                onAdded = {},
                onRemoved = {}
            ) {
                FirstImagePreview(id = "new-row", params = it, onFocus = {}, onRemove = {})
            }

            else -> Text("Unsupported table column type '${column.type}'")
        }
    }

    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
        OutlinedButton(
            modifier = Modifier.testTag("${title}-capture-cancel"),
            onClick = onClose,
            shape = RoundedCornerShape(8.dp),
        ) {
            Text("Cancel")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            modifier = Modifier.testTag("${title}-capture-submit"),
            onClick = onClose,
            shape = RoundedCornerShape(8.dp),
        ) {
            Text("Submit")
        }
    }
}


@Composable
private fun Preview(
    editor: TableComponentEditor,
    rows: Int,
    totalRows: Int,
    onClick: () -> Unit,
) {
    val component = remember(editor) { editor.component }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        JoyTitle(component)
        Row {
            TextButton(onClick = onClick, contentPadding = PaddingValues(0.dp), shape = RoundedCornerShape(2.dp)) {
                Text("View")
                Icon(Icons.AutoMirrored.Outlined.ArrowRight, "View table")
                if (totalRows != 0) Text("+$totalRows", modifier = Modifier.padding(start = 4.dp))
            }
        }
    }
    Column(
        modifier = Modifier
            .border(1.dp, color = LocalContentColor.current.copy(alpha = 0.6f), shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
    ) {
        RowArrayTable(
            cols = component.columns.size,
            rows = rows,
            modifier = Modifier
                .testTag("${component.id}-small")
                .fillMaxWidth()
                .clickable { onClick() }
        ) { row, col ->
            val width = if (col < 0) 30.dp else colWidth(component.columns[col].type, preview = true)
            val modifier = Modifier.width(width).padding(horizontal = 8.dp)
            when {
                col == -1 && row == -1 -> Box(modifier = modifier)
                col == -1 && row != -1 -> Text(text = "${row + 1}", modifier = modifier)
                col != -1 && row == -1 -> Text(text = component.columns[col].title, modifier = modifier)
                else /* col != -1 && row != -1 */ -> when (val cell = editor.rows.get(row)?.col(col)) {
                    is TextCellEditor -> Text(text = cell.value ?: "", modifier = modifier)
                    is DropdownCellEditor -> Text(text = cell.selected()?.value ?: "", modifier = modifier)
                    is ImageCellEditor -> Text(text = "${cell.value.size}", modifier = modifier)
                }
            }
        }
    }
}

private fun colWidth(type: Component.Type, preview: Boolean) = when (type) {
    Component.Type.text -> 150.dp
    Component.Type.dropdown -> 200.dp
    Component.Type.image -> if (preview) 100.dp else 200.dp
    else -> 100.dp
}