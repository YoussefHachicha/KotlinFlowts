package com.youssef.kotlinflowts.app.gallery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.youssef.kotlinflowts.builder.kotlinflowts.LayoutBuilder
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component

@Composable
fun GallerySelector(
    layoutComponents: List<ComponentEditor>,
    builders: MutableMap<String, LayoutBuilder>,
    onExpandChange: (Boolean) -> Unit = {},
    add: (LayoutBuilder?) -> Unit
) {
    val columns by remember(layoutComponents) { mutableStateOf(layoutComponents.filter { it.type == Component.Type.column }) }
    val rows by remember(layoutComponents) { mutableStateOf(layoutComponents.filter { it.type == Component.Type.row }) }

    Dialog(
        onDismissRequest = { onExpandChange(false) },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Box {
            Surface(
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp).fillMaxSize(0.9f)
            ) {
                LazyColumn {
                    itemsIndexed(columns) { index, layout ->
                        GalleryItem("${layout.title} #${index + 1}") {
                            onExpandChange(false)
                            add(builders[layout.id])
                        }
                    }
                    itemsIndexed(rows) { index, layout ->
                        GalleryItem("${layout.title} #${index + 1}") {
                            onExpandChange(false)
                            add(builders[layout.id])
                        }
                    }
                    item {
                        GalleryItem("Add as a child") {
                            onExpandChange(false)
                            add(null)
                        }
                    }
                }
            }
        }
    }
}