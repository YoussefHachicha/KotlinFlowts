package com.youssef.kotlinflowts.app.gallery

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.builder.kotlinflowts.LayoutBuilder
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.AppEditor
import com.youssef.kotlinflowts.models.kotlinflowts.Screen
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ComponentId

@Composable
fun ComponentsGallerySample(
    editor: AppEditor,
    currentScreen: Screen?,
    updateUi: Int,
    builders: MutableMap<ComponentId, LayoutBuilder>,
    add: (Component.Type) -> Unit
) {

    val editorLayoutComponents by remember(
        editor,
        currentScreen,
        updateUi,
    ) {
        mutableStateOf(editor.components.layoutsFrom(currentScreen))
    }

    val componentsTypes = Component.Type.entries
    var expanded by remember { mutableStateOf(false) }
    var selectedComponentType by remember { mutableStateOf(Component.Type.text) }

    LazyColumn {
        items(componentsTypes) { type ->
            GalleryItem(type.name) {
                selectedComponentType = type
                if (editorLayoutComponents.isNotEmpty()) {
                    expanded = true
                } else {
                    add(type)
                }
            }
        }
    }

    if (expanded)
        GallerySelector(
            layoutComponents = editorLayoutComponents,
            builders = builders,
            onExpandChange = { expanded = it },
        ) { builder ->
            if (builder == null) {
                add(selectedComponentType)
            } else {
                println("Custom Builder $builder")
                builder.add(selectedComponentType)
            }
        }
}