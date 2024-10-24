package com.youssef.kotlinflowts.compose.joyfill.tables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun LazyRowArrayTable(
    cols: Int,
    rows: Int,
    modifier: Modifier = Modifier,
    rowHeight: Dp = 60.dp,
    key: ((row: Int) -> Any)? = null,
    state: LazyListState = rememberLazyListState(),
    cell: @Composable (row: Int, col: Int) -> Unit,
) = Column(
    modifier = modifier
        .border(0.01.dp, color = LocalContentColor.current, shape = RoundedCornerShape(8.dp))
        .clip(RoundedCornerShape(8.dp))
        .horizontalScroll(rememberScrollState()),
) {
    Row( // Table Header
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (col in -1 until cols) Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.height(rowHeight).borders()
        ) {
            cell(-1, col)
        }
    }
    LazyColumn(
        state = state,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(rows, key) { row ->
            val alpha = if (row % 2 == 0) 0.1f else 0.2f
            val color = LocalContentColor.current.copy(alpha = alpha)
            Row(
                modifier = Modifier.fillMaxWidth().background(color).height(rowHeight),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (col in -1 until cols) Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier.height(rowHeight).borders()
                ) {
                    cell(row, col)
                }
            }
        }
    }
}

@Composable
internal fun Modifier.borders(width: Dp = 1.dp, color: Color = LocalContentColor.current.copy(alpha = 0.4f)) = drawBehind {
    val strokeWidth = width.toPx()
    val x = size.width - strokeWidth / 2
    val y = size.height - strokeWidth / 2
    val path = Path().apply {
        moveTo(x, 0f)
        lineTo(x, y)
        lineTo(0f, y)
    }
    drawPath(path, color, style = Stroke(strokeWidth))
}