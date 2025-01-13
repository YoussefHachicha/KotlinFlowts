package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.DateFieldComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.DateFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class DateFieldFieldComponentEditorImpl(
    app: App,
    override val comp: DateFieldComponent,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractValueBasedComponentEditor<Long, DateFieldComponent>(app, comp, onChange), DateFieldComponentEditor {
    override fun generateCode(): String {
        return """
            var dialog by remember { mutableStateOf(false) }
            val pattern = remember { "{YYYY}-{MM}-{DD}" }
            
            
            enum class Picking {
               Date, Time;
            }
            
            fun String.requiresDate() = listOf("YY", "MM", "DD").any { contains(it) }
            fun String.requiresTime() = listOf("hh", "mm").any { contains(it) }

            
            val initialPicking = remember(pattern) { if (pattern.requiresDate()) Picking.Date else Picking.Time }

            var picking by remember(initialPicking) { mutableStateOf(initialPicking) }
            val date = rememberDatePickerState(
                initialSelectedDateMillis = component.value,
                selectableDates = object : SelectableDates {
                    override fun isSelectableDate(utcTimeMillis: Long): Boolean = mode == Mode.fill
                    override fun isSelectableYear(year: Int): Boolean = mode == Mode.fill
                },
                initialDisplayMode = DisplayMode.Picker
            )
            
            val t = value?.toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())

            val time = rememberTimePickerState(
                initialHour = t?.hour ?: 0,
                initialMinute = t?.minute ?: 0
            )

            fun String.format(date: Long?, hr: Int?, min: Int?): String {
                if (date != null) {
                    val dateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC)

                    return this.replace("{YYYY}", dateTime.year.toString().padStart(4, '0'))
                        .replace("{MM}", dateTime.monthNumber.toString().padStart(2, '0'))
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

 
            Column(modifier = Modifier.fillMaxWidth()) {
                if (!${disableTitle})
                    Text($title)
                OutlinedTextField(
                    value = pattern.format(date.selectedDateMillis, time.hour, time.minute),
                    onValueChange = {},
                    interactionSource = interaction,
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = $borderColor,
                        unfocusedBorderColor = $borderColor,
                    ),
                )
            }

            if (dialog) Dialog(onDismissRequest = {
               dialog = false
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
                                modifier = Modifier,
                                onClick = {
                                    dialog = false
                                },
                                shape = RoundedCornerShape(8.dp),
                            ) {
                                Text("Cancel")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                modifier = Modifier,
                                onClick = {
                                    when (picking) {
                                        Picking.Date -> {
                                            if (pattern.requiresTime()) {
                                                picking = Picking.Time
                                            } else {
                                                val millis = date.selectedDateMillis
                                                dialog = false
                                                //change value here
                                            }
                                        }

                                        Picking.Time -> {
                                            val minutes = time.minute + (time.hour * 60)
                                            val millis = (date.selectedDateMillis ?: 0L) + (minutes * 60 * 1000L)
                                            dialog = false
                                            //change value here
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
        """.trimIndent()
    }

    override fun changeValue(value: Long) {
        this.value = value
        notifyChange(value)
    }
}