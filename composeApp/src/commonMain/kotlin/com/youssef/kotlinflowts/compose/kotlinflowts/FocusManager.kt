package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.ui.focus.FocusState

internal class FocusManager<out T>(
    private val onChanged: (Signal<T>) -> Unit,
    private val prepare: () -> Unit
) {
    private var blurCount = 0
    val handler = { state: FocusState ->
        if (!state.hasFocus) {
            blurCount = minOf(blurCount + 1, 2)
            if (blurCount > 1) {
                prepare()
                onChanged(Signal.Blur(Unit))
            }
        } else {
            onChanged(Signal.Focus)
        }
    }
}