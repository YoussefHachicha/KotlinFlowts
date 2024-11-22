package com.youssef.kotlinflowts.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.app.editor.EditorSample
import com.youssef.kotlinflowts.app.gallery.ComponentsGallerySample
import com.youssef.kotlinflowts.app.view.ViewSample
import com.youssef.kotlinflowts.compose.kotlinflowts.rememberEditor
import com.youssef.kotlinflowts.models.kotlinflowts.toMutableScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val appBuilder = remember { Service.getAppBuilder() }
        val editor = rememberEditor(appBuilder.app)
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Panel("Gallery"){
                ComponentsGallerySample(
                    editor = editor,
                    updateUi = appBuilder.updateUi,
                    currentScreen = appBuilder.app.cursor,
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
                EditorSample(
                    editor = editor,
                    currentScreen = appBuilder.app.cursor,
                )
            }
        }
    }
}