package com.youssef.kotlinflowts.compose.joyfill

internal sealed interface Signal<out T> {
    data object Focus : Signal<Nothing>
    data class Blur(val value: Any?) : Signal<Nothing>
    data class Change<out T>(val value: T) : Signal<T>
}