package com.youssef.kotlinflowts.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.youssef.kotlinflowts.app.gallery.ComponentsGallerySample
import com.youssef.kotlinflowts.app.view.ViewSample
import com.youssef.kotlinflowts.app.view.service
import com.youssef.kotlinflowts.compose.kotlinflowts.rememberEditor
import com.youssef.kotlinflowts.models.kotlinflowts.toMutableScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val appBuilder = remember { service.getAppBuilder() }
        val editor = rememberEditor(appBuilder.app)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Panel("Gallery"){
                ComponentsGallerySample(
                    editor = editor,
                    updateUi = appBuilder.updateUi,
                    currentScreen = appBuilder.cursor,
                    builders = appBuilder.app.builders,
                ){
                    appBuilder.add(it)
                }
            }
            Panel("View"){
                ViewSample(
                    editor = editor,
                    updateUi = appBuilder.updateUi,
                ){
                    appBuilder.updateCursor(it.toMutableScreen())
                }
            }
            Panel("Editor"){

            }
        }
    }
}