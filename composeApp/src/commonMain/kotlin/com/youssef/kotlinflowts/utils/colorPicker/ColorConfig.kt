package com.youssef.kotlinflowts.utils.colorPicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.utils.clickableNoIndication

@Composable
fun ColorConfig(
    colors: List<Color> = listOf(
        Color.Black,
        Color(0xff0057bb),
        Color(0xff02f185),
        Color(0xFFf10207),
        Color(0xFFf36b03),
    ),
    selectedColor: Color,
    onColorChanged: (Color) -> Unit,
    isCollapsed: Boolean,
    icon: ImageVector = Icons.Outlined.ColorLens,
    isRow: Boolean = false,
) {
    var customSelectedColor by remember(selectedColor) {
        mutableStateOf(selectedColor)
    }
    var showColorDialog by remember {
        mutableStateOf(false)
    }

    if (isCollapsed) {
        Box {
            var showColorControl by remember { mutableStateOf(false) }

            IconButton(
                onClick = { showColorControl = true },
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = selectedColor.copy(alpha = selectedColor.alpha.coerceAtLeast(.5f))
                )
            }

            DropdownMenu(
                expanded = showColorControl,
                onDismissRequest = { showColorControl = false },
                offset =
                if (isRow)
                    DpOffset.Zero
                else
                    DpOffset(x = (-240).dp, y = (-50).dp),
                modifier = Modifier
                    .width(240.dp)
                    .padding(horizontal = 6.dp)
            ) {
                ColorConfigRow(
                    colors = colors,
                    selectedColor = selectedColor,
                    customSelectedColor = customSelectedColor,
                    onColorChanged = onColorChanged,
                    openColorDialog = { showColorDialog = true },
                )
            }
        }
    } else {
        ColorConfigRow(
            colors = colors,
            selectedColor = selectedColor,
            customSelectedColor = customSelectedColor,
            onColorChanged = onColorChanged,
            openColorDialog = { showColorDialog = true },
        )
    }

    if (showColorDialog) {
        SelectColorDropdown(
            color = customSelectedColor,
            onSubmitColor = { color ->
                onColorChanged(color)
                customSelectedColor = color
                showColorDialog = false
            },
            onCloseRequest = { showColorDialog = false },
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ColorConfigRow(
    colors: List<Color>,
    selectedColor: Color,
    customSelectedColor: Color,
    onColorChanged: (Color) -> Unit,
    openColorDialog: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .weight(1f)
        ) {
            colors.forEach { color ->
                ColorButton(
                    color = color,
                    onClick = { onColorChanged(color) },
                    selected = selectedColor == color,
                    modifier = Modifier
                        .size(22.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .clickableNoIndication { openColorDialog() }
                .clip(RoundedCornerShape(6.dp))
                .border(
                    width = 1.8.dp,
                    color =
                    if (customSelectedColor == selectedColor)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .background(
                        color = customSelectedColor,
                        shape = RoundedCornerShape(6.dp)
                    ),
            )

            Image(
                Icons.Outlined.ColorLens,
                contentDescription = "ColorWheel",
                modifier = Modifier
                    .size(32.dp)
                    .padding(2.dp),
            )
        }
    }
}
