package com.youssef.kotlinflowts.compose.kotlinflowts.utils

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.pointerInput

internal actual fun Modifier.onDrawing(
    started: () -> Unit,
    ended: () -> Unit,
    progressing: (Offset) -> Unit
): Modifier = this.pointerInput(Unit) {
    awaitEachGesture {
        awaitFirstDown()
        started()
        do {
            //This PointerEvent contains details including
            // event, id, position and more
            val event: PointerEvent = awaitPointerEvent()
            // ACTION_MOVE loop
            event.changes.forEach { progressing(it.position) }
        } while (event.changes.any { it.pressed })
        ended()
    }
}