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
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val app = remember { service.getApp() }
        val editor = rememberEditor(app)

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Panel("Gallery"){
                ComponentsGallerySample()
            }
            Panel("View"){
                ViewSample(editor)
            }
            Panel("Editor"){

            }
        }
    }
}