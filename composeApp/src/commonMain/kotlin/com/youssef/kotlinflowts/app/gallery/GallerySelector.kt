package com.youssef.kotlinflowts.app.gallery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.youssef.kotlinflowts.builder.kotlinflowts.LayoutBuilder
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component


@Composable
fun GallerySelector(
    columnComponents: List<Component>,
    rowComponents: List<Component>,
    expanded: Boolean,
    builders: MutableMap<String, LayoutBuilder>,
    onExpandChange: (Boolean) -> Unit = {},
    add: (LayoutBuilder?) -> Unit
) {
    if (expanded) Dialog(
        onDismissRequest = { onExpandChange(false) },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Box {
            Surface(
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp).fillMaxSize(0.9f)
            ) {
                LazyColumn {
                    items(columnComponents) { column ->
                        GalleryItem(column.title) {
                            onExpandChange(false)
                            add(builders[column.id])
                        }
                    }
                    items(rowComponents) { row ->
                        GalleryItem(row.title) {
                            onExpandChange(false)
                            add(builders[row.id])
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