package com.youssef.kotlinflowts.app

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.Panel(
    panelTitle: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .fillMaxSize()
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(panelTitle)
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}