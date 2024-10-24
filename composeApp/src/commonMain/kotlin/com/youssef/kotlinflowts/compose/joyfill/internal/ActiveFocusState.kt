package com.youssef.kotlinflowts.compose.joyfill.internal

import androidx.compose.ui.focus.FocusState

data object ActiveFocusState : FocusState {
    override val hasFocus = true
    override val isCaptured = true
    override val isFocused = true
}