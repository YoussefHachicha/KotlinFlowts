package com.youssef.kotlinflowts.compose.joyfill.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset

internal expect fun Modifier.onDrawing(
    started: () -> Unit,
    ended: () -> Unit,
    progressing: (Offset) -> Unit,
): Modifier