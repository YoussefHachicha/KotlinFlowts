package com.youssef.kotlinflowts.compose.kotlinflowts.internal

import androidx.compose.ui.focus.FocusState

data object InActiveFocusState : FocusState {
    override val hasFocus = false
    override val isCaptured = false
    override val isFocused = false
}