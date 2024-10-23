package com.youssef.kotlinflowts

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.youssef.kotlinflowts.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinFlowts",
    ) {
        App()
    }
}