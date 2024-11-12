package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color

@Composable
internal fun RawTextComponent(
    value: String?,
    borders: Boolean = true,
    modifier: Modifier = Modifier,
    onChange: (String) -> Unit,
    maxLines: Int = Int.MAX_VALUE,
    readonly: Boolean = false,
    minLines: Int = 1,
    onFocusChanged: (FocusState) -> Unit,
) {
    var v by remember(value) { mutableStateOf(value ?: "") }
    OutlinedTextField(
        value = v,
        onValueChange = {
            v = it
            onChange(it)
        },
        readOnly = readonly,
        modifier = modifier.onFocusChanged(onFocusChanged),
        singleLine = maxLines == 1,
        maxLines = maxLines,
        minLines = minLines,
        colors = if (borders) {
            OutlinedTextFieldDefaults.colors()
        } else {
            OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            )
        }
    )
}