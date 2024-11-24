package com.youssef.kotlinflowts.utils.colorPicker

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import colorPicker.picker.CustomColorPicker

@Composable
fun SelectColorDropdown(
    color: Color,
    onSubmitColor: (Color) -> Unit,
    onCloseRequest: () -> Unit,
) {
    var customColor by remember {
        mutableStateOf(color)
    }

    DropdownMenu(
        expanded = true,
        onDismissRequest = onCloseRequest,
        modifier = Modifier,
        content = {
            CustomColorPicker(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = customColor,
                showAlphaBar = true,
                onColorChanged = { color ->
                    customColor = color.toColor()
                }
            )

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    onSubmitColor(customColor)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "select",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                )
            }
        }
    )
}