package com.youssef.kotlinflowts.compose.joyfill

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.youssef.kotlinflowts.editor.joyfill.chart.LineEditor
import com.youssef.kotlinflowts.editor.joyfill.chart.PointEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ChartComponentEditor
import com.youssef.kotlinflowts.manager.joyfill.Mode
import com.youssef.kotlinflowts.models.joyfill.fields.ChartComponent

@Composable
internal fun JoyChartField(
    editor: ChartComponentEditor,
    mode: Mode,
    onSignal: (Signal<Any?>) -> Unit,
) = Column(modifier = Modifier.testTag(editor.component.id).fillMaxWidth()) {
    val field = remember(editor) { editor.component }
    var capturing by remember { mutableStateOf(false) }
    val lines = remember(field.value) { mutableStateListOf(*(field.value ?: emptyList()).toTypedArray()) }
    val readonly = field.disabled || mode == Mode.readonly

    JoyTitle(field.title, modifier = Modifier.testTag("${field.id}-preview-title"))
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedButton(
        onClick = {
            capturing = true
            onSignal(Signal.Focus)
        },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.testTag("${field.id}-preview-button").fillMaxWidth().height(80.dp)
    ) {
        Icon(Icons.Outlined.BarChart, "${field.id}-preview-icon", modifier = Modifier.size(80.dp))
    }

    if (capturing) Dialog(onDismissRequest = {
        capturing = false
        onSignal(Signal.Focus)
    }, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Surface(
            modifier = Modifier.testTag("${field.id}-capture").padding(
                vertical = 16.dp, horizontal = 8.dp
            ).fillMaxSize(0.96f)
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    JoyTitle(field.title, modifier = Modifier.testTag("${field.id}-capture-title"))
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "${field.id}-capture-close",
                        modifier = Modifier.clickable {
                            capturing = false
                            onSignal(Signal.Blur(Unit))
                        }.padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                val last = lines.lastOrNull()

                Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
                    for (line in lines) Column(modifier = Modifier.fillMaxWidth()) {
                        LineEditor(
                            field = field,
                            editor = editor.lines.find(line.id) ?: error("Line not found"),
                            onDelete = { lines.remove(line) },
                            readonly = readonly,
                        )
                        if (line != last) Spacer(modifier = Modifier.height(12.dp))
                    }

                    if (!readonly) {
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedButton(
                            onClick = {
                                val line = editor.lines.add(title = "Line ${lines.size + 1}")
                                lines.add(line.line)
                            },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                                .testTag("${field.id}-line-add")
                                .height(40.dp)
                        ) {
                            Text("Add Chart Line")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LineEditor(
    field: ChartComponent,
    editor: LineEditor,
    onDelete: () -> Unit,
    readonly: Boolean,
    modifier: Modifier = Modifier
) = Deletable(readonly = readonly, onDelete = onDelete, modifier = modifier) {
    val line = remember(editor) { editor.line }
    var name by remember(line) { mutableStateOf(line.title) }
    var description by remember(line) { mutableStateOf(line.description) }
    // something is preventing add and remove triggers for first events when using mutableStateListOf
//     val points = remember(line.points) { mutableStateListOf(*line.points.toTypedArray()) }
    var points by remember(line.points) { mutableStateOf(line.points) }

    Text("Title")
    RawTextField(
        value = name,
        onChange = { name = it },
        borders = true,
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        maxLines = 1,
        readonly = readonly,
        onFocusChanged = {}
    )

    Text("Description")
    RawTextField(
        value = description,
        onChange = { description = it },
        borders = true,
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        maxLines = 1,
        readonly = readonly,
        onFocusChanged = {}
    )

    if (points.isNotEmpty()) {
        Text("Points")
        Spacer(modifier = Modifier.height(8.dp))
    }
    for (point in points) {
        PointEditor(
            editor = editor.find(point.id) ?: error("Point not found"),
            field = field,
            onDelete = { editor.remove(point.id) },
            readonly = readonly
        )
        Spacer(modifier = Modifier.height(8.dp))
    }

    if (!readonly) {
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = {
                val point = editor.add()
                points = (points + point.point).toMutableList()
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().height(40.dp)
        ) {
            Text("Add Point")
        }
    }
}

@Composable
private fun PointEditor(
    field: ChartComponent,
    editor: PointEditor,
    onDelete: () -> Unit,
    readonly: Boolean = false,
    modifier: Modifier = Modifier
) = Deletable(readonly, onDelete, modifier = modifier) {

    val point = remember(editor) { editor.point }
    var label by mutableStateOf(point.label)
    Text("Label")
    Spacer(modifier = Modifier.height(8.dp))
    RawTextField(
        value = label,
        onChange = {
            label = it
            editor.label(it)
        },
        borders = true,
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        readonly = readonly,
        onFocusChanged = {}
    )

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        NumberEditor(
            label = field.x.label,
            number = point.x,
            onChange = { editor.x(it) },
            readonly = readonly,
            modifier = Modifier.weight(1f).padding(end = 4.dp)
        )
        NumberEditor(
            label = field.y.label,
            number = point.y,
            onChange = { editor.y(it) },
            readonly = readonly,
            modifier = Modifier.weight(1f).padding(start = 4.dp)
        )
    }
}

@Composable
private fun NumberEditor(
    label: String,
    number: Double,
    onChange: (Double) -> Unit,
    readonly: Boolean = false,
    modifier: Modifier = Modifier
) = Column(modifier) {
    var value by mutableStateOf(number.toString())
    Text(label)
    Spacer(modifier = Modifier.height(8.dp))
    RawTextField(
        value = value,
        onChange = {
            val num = it.toDoubleOrNull() ?: 0.0
            value = num.toString()
            onChange(num)
        },
        borders = true,
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        readonly = readonly,
        onFocusChanged = {}
    )
}

@Composable
private fun Deletable(
    readonly: Boolean,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) = Box(modifier = modifier.fillMaxWidth()) {
    Column(
        modifier = Modifier.padding(4.dp).border(
            width = 2.dp,
            shape = RoundedCornerShape(8.dp),
            color = LocalContentColor.current.copy(alpha = 0.5f)
        ).padding(8.dp),
        content = content
    )
    if (!readonly) Icon(
        imageVector = Icons.Filled.Delete,
        contentDescription = "Delete point",
        tint = LocalContentColor.current,
        modifier = Modifier.align(Alignment.TopEnd)
            .clip(CircleShape)
            .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
            .clickable { onDelete() }
            .padding(2.dp)
    )
}