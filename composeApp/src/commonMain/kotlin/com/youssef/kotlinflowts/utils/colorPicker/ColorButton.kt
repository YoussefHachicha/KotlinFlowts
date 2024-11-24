package com.youssef.kotlinflowts.utils.colorPicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.utils.clickableNoIndication

@Composable
fun ColorButton(
    modifier: Modifier = Modifier,
    color: Color,
    onClick: () -> Unit,
    isTransparent: Boolean = color == Color.Transparent,
    selected: Boolean = false,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    content: @Composable () -> Unit = {}
) {
    if (isTransparent)
        Box(
            modifier = modifier
                .border(
                    width = 2.dp,
                    color = if (selected) MaterialTheme.colorScheme.outline else Color.LightGray,
                    shape = RoundedCornerShape(6.dp)
                )
                .drawBehind {
                    drawLine(
                        color = Color.Red,
                        start = Offset(2f, this.size.height - 2f),
                        end = Offset(this.size.width - 2f, 2f),
                        strokeWidth = 2f
                    )
                }
                .clickableNoIndication {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    else
        Box(
            modifier = modifier
                .border(
                    width = 2.dp,
                    color = if (selected) MaterialTheme.colorScheme.outline else Color.Transparent,
                    shape = RoundedCornerShape(6.dp)
                )
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(paddingValues)
                .clickableNoIndication(onClick = onClick)
                .background(
                    color = color,
                    shape = RoundedCornerShape(6.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
}