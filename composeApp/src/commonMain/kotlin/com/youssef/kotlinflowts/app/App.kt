package com.youssef.kotlinflowts.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.app.editor.EditorSample
import com.youssef.kotlinflowts.app.gallery.ComponentsGallerySample
import com.youssef.kotlinflowts.app.view.ScreenNavigator
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
            horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.fillMaxSize()
        ) {
            Panel("Gallery") {
                ComponentsGallerySample(
                    editor = editor,
                    updateUi = appBuilder.addCompUpdate,
                    currentScreen = appBuilder.app.cursor,
                    builders = appBuilder.app.builders,
                ) {
                    appBuilder.add(it)
                }
            }

            Column(modifier = Modifier.weight(1f)) {

                val screens = remember(editor, appBuilder.addScreenUpdate) {
                     editor.screens.raw()
                }

                var currentScreen by remember(screens) {
                    mutableStateOf(screens.first())
                }

                Panel(
                    panelTitle = "View",
                    modifier = Modifier.weight(0.9f),
                ) {
                    ViewSample(
                        updateUi = appBuilder.addCompUpdate,
                        editor = editor,
                        currentScreen = currentScreen
                    ) {
                        appBuilder.updateCursor(it.toMutableScreen())
                    }
                }
                ScreenNavigator(
                    screens = screens,
                    currentScreen = currentScreen,
                    onChange = {
                        currentScreen = it
                    },
                    onAdd = {
                        currentScreen = appBuilder.screen(null)
                    },
                    generateCode = {
                        val comp = editor.components.from(currentScreen).map { it.comp }
                        //now i need to printLn the generated code
                        comp.forEach {
                            println(it.generateCode())
                        }
                    },
                    modifier = Modifier.weight(0.1f),
                )
            }


            Panel("Editor") {
                EditorSample(
                    editor = editor,
                ) { id, builderId ->
                    appBuilder.app.builders[builderId]?.delete(id)
                    if (editor.selectedEditorComponent?.id == id) {
                        editor.selectedEditorComponent = null
                    }
                }
            }
        }
    }

}


