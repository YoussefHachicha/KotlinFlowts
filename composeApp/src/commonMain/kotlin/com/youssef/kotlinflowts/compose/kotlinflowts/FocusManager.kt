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
//The FocusManager class is designed to manage focus state changes for a UI component in a Compose application. Here's a breakdown of its functionality:
//Constructor Parameters:
//onChanged: A function that takes a Signal<T> and is called when the focus state changes.
//prepare: A function that is called to prepare for a blur event.
//Properties:
//blurCount: An integer that keeps track of the number of times the component has lost focus.
//Handler:
//handler: A lambda function that takes a FocusState and handles focus changes.
//If the component does not have focus (!state.hasFocus):
//Increment blurCount by 1, but cap it at 2.
//If blurCount is greater than 1, call prepare() and then call onChanged with a Signal.Blur event.
//If the component gains focus (state.hasFocus):
//Call onChanged with a Signal.Focus event.
//This class helps manage focus events and ensures that certain actions are taken when the component gains or loses focus, such as preparing for a blur event and signaling the change.