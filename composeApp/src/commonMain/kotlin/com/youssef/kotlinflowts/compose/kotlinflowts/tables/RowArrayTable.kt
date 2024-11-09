package com.youssef.kotlinflowts.compose.kotlinflowts.tables

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun RowArrayTable(
    cols: Int,
    rows: Int,
    modifier: Modifier = Modifier,
    cell: @Composable (row: Int, col: Int) -> Unit,
) = Column(modifier = modifier.horizontalScroll(rememberScrollState())) {
    Row( // Table Header
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (col in -1 until cols) {
            cell(-1,col)
        }
    }
    for (row in 0 until rows) {
        val alpha = if (row % 2 == 0) 0.1f else 0.2f
        val color = LocalContentColor.current.copy(alpha = alpha)
        Row(
            modifier = Modifier.fillMaxWidth().background(color),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (col in -1 until cols) {
                cell(row, col)
            }
        }
    }
}