package com.youssef.kotlinflowts.app.gallery

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

    val componentsTypes = remember {
        Component.Type.entries.filterNot {
            it == Component.Type.richText ||
                    it == Component.Type.table ||
                    it == Component.Type.chart ||
                    it == Component.Type.file ||
                    it == Component.Type.block ||
                    it == Component.Type.unknown

        }
    }
    var expanded by remember { mutableStateOf(false) }
    var selectedComponentType by remember { mutableStateOf(Component.Type.textField) }

    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
    ) {
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
                builder.add(selectedComponentType)
            }
        }
}