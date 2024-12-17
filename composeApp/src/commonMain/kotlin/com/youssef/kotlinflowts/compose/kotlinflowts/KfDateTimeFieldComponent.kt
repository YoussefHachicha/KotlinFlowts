package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DateFieldComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.Mode
import kotlinx.coroutines.launch

@Composable
internal fun KfDateTimeFieldComponent(
    modifier: Modifier = Modifier,
    editor: DateFieldComponentEditor,
    format: String?,
    mode: Mode,
    onSignal: (Signal<Long?>) -> Unit,
) = Column(modifier) {
    KfDateTimeComponentImpl(editor, format, mode, onSignal)
}

@Composable
internal fun ColumnScope.KfDateTimeFieldComponent(
    modifier: Modifier = Modifier,
    editor: DateFieldComponentEditor,
    format: String?,
    mode: Mode,
    onSignal: (Signal<Long?>) -> Unit,
) = Column(modifier) {
    KfDateTimeComponentImpl(editor, format, mode, onSignal)
}

@Composable
internal fun RowScope.KfDateTimeFieldComponent(
    modifier: Modifier = Modifier,
    editor: DateFieldComponentEditor,
    format: String?,
    mode: Mode,
    onSignal: (Signal<Long?>) -> Unit,
) = Column(modifier.weight(1f)) {
    KfDateTimeComponentImpl(editor, format, mode, onSignal)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun KfDateTimeComponentImpl(
    editor: DateFieldComponentEditor,
    format: String?,
    mode: Mode,
    onSignal: (Signal<Long?>) -> Unit,
) {
    val component = remember(editor) { editor.comp }
    val value = component.value?.let { java.time.Instant.ofEpochMilli(it) }
    var dialog by remember { mutableStateOf(false) }
    val readonly = remember(component, mode) { editor.disabled || mode == Mode.readonly }

    val pattern = remember {
        format?.replace("YYYY", "{YYYY}")
            ?.replace("MM", "{MM}")
            ?.replace("DD", "{DD}")
            ?.replace("hh", "{hh}")
            ?.replace("mm", "{mm}")
            ?.replace("a", "{ampm}")
            ?: "{YYYY}-{MM}-{DD}"
    }

    val initialPicking = remember(pattern) { if (pattern.requiresDate()) Picking.Date else Picking.Time }

    var picking by remember(initialPicking) { mutableStateOf(initialPicking) }

    val date = rememberDatePickerState(
        initialSelectedDateMillis = component.value,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean = mode == Mode.fill
            override fun isSelectableYear(year: Int): Boolean = mode == Mode.fill
        },
        initialDisplayMode = if (mode == Mode.readonly) DisplayMode.Input else DisplayMode.Picker
    )

    val t = value?.atZone(java.time.ZoneId.systemDefault())

    val time = rememberTimePickerState(
        initialHour = t?.hour ?: 0,
        initialMinute = t?.minute ?: 0
    )

    val interaction = remember { MutableInteractionSource() }

    LaunchedEffect(interaction, readonly) {
        if (!readonly) launch {
            interaction.interactions.collect {
                if (it is PressInteraction.Press) {
                    picking = initialPicking
                    dialog = true
                    onSignal(Signal.Focus)
                }
            }
        }
    }

    Column(modifier = Modifier.testTag(component.id).fillMaxWidth()) {
        if (!editor.disableTitle)
            KfTitle(
                editor,
                modifier = Modifier.testTag("${component.id}-title")
            )

        OutlinedTextField(
            value = pattern.format(date.selectedDateMillis, time.hour, time.minute),
            onValueChange = {},
            interactionSource = interaction,
            modifier = Modifier.testTag("${component.id}-body-output").fillMaxWidth(),
            readOnly = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = editor.borderColor,
                unfocusedBorderColor = editor.borderColor,
            ),
        )
    }

    if (dialog) Dialog(onDismissRequest = {
        dialog = false
        onSignal(Signal.Blur(value?.toEpochMilli()))
    }) {
        Surface(modifier = Modifier.fillMaxWidth(0.95f)) {
            Column(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
                when (picking) {
                    Picking.Date -> DatePicker(
                        state = date,
                        title = null,
                        headline = null,
                        modifier = Modifier.testTag("${component.id}-input-date").padding(14.dp),
                    )

                    Picking.Time -> TimePicker(
                        state = time,
                        modifier = Modifier.testTag("${component.id}-input-time")
                    )
                }

                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
                    OutlinedButton(
                        modifier = Modifier.testTag("${component.id}-input-cancel"),
                        onClick = {
                            dialog = false
                            onSignal(Signal.Blur(value?.toEpochMilli()))
                        },
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        modifier = Modifier.testTag("${component.id}-input-submit"),
                        onClick = {
                            when (picking) {
                                Picking.Date -> {
                                    if (pattern.requiresTime()) {
                                        picking = Picking.Time
                                    } else {
                                        val millis = date.selectedDateMillis
                                        onSignal(Signal.Change(millis))
                                        dialog = false
                                        onSignal(Signal.Blur(millis))
                                        editor.value = millis
                                    }
                                }

                                Picking.Time -> {
                                    val minutes = time.minute + (time.hour * 60)
                                    val millis = (date.selectedDateMillis ?: 0L) + (minutes * 60 * 1000L)
                                    onSignal(Signal.Change(millis))
                                    dialog = false
                                    onSignal(Signal.Blur(millis))
                                    editor.value = millis
                                }
                            }
                        },
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text("Submit")
                    }
                }
            }
        }
    }
}

private fun String.format(date: Long?, hr: Int?, min: Int?): String {
    if (date != null) {
        val dateTime = java.time.Instant.ofEpochMilli(date)
            .atZone(java.time.ZoneId.systemDefault())

        return this.replace("{YYYY}", dateTime.year.toString().padStart(4, '0'))
            .replace("{MM}", dateTime.monthValue.toString().padStart(2, '0'))
            .replace("{DD}", dateTime.dayOfMonth.toString().padStart(2, '0'))
            .replace("{hh}", (hr ?: 0).toString().padStart(2, '0'))
            .replace("{mm}", (min ?: 0).toString().padStart(2, '0'))
            .replace("{ampm}", if ((hr ?: 0) >= 12) "PM" else "AM")
    }

    if (requiresDate()) {
        return ""
    }

    if (requiresTime() && hr == null) {
        return ""
    }

    // Format time only
    return this.replace("{YYYY}", "0000")
        .replace("{MM}", "01")
        .replace("{DD}", "01")
        .replace("{hh}", (hr ?: 0).toString().padStart(2, '0'))
        .replace("{mm}", (min ?: 0).toString().padStart(2, '0'))
        .replace("{ampm}", if ((hr ?: 0) >= 12) "PM" else "AM")
}

private fun String.requiresDate() = listOf("YY", "MM", "DD").any { contains(it) }
private fun String.requiresTime() = listOf("hh", "mm").any { contains(it) }
private enum class Picking {
    Date, Time;
}