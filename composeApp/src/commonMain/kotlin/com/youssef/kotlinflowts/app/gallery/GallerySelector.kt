package com.youssef.kotlinflowts.app.gallery

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.youssef.kotlinflowts.builder.kotlinflowts.LayoutBuilder
import com.youssef.kotlinflowts.editor.kotlinflowts.column.ColumnComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.row.RowComponentEditor

@Composable
fun GallerySelector(
    layoutComponents: List<ComponentEditor>,
    builders: MutableMap<String, LayoutBuilder>,
    onExpandChange: (Boolean) -> Unit = {},
    add: (LayoutBuilder?) -> Unit
) {
    Dialog(
        onDismissRequest = { onExpandChange(false) },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
                .fillMaxSize(0.9f)
        ) {
            LazyColumn(
                modifier = Modifier.padding(8.dp)
            ) {
                itemsIndexed(layoutComponents) { index, layout ->
                    when (layout) {
                        is ColumnComponentEditor -> {
                            val layouts =
                                remember(layout.columnComponents.all) { mutableStateOf(layout.columnComponents.all.value.filter { it.isLayout() }) }

                            GalleryItem(
                                text = "${layout.title} #${index + 1}",
                                childLayouts = layouts.value,
                                layoutId = layout.id,
                            ) {
                                onExpandChange(false)
                                add(builders[ it ?: layout.id])
                            }
                        }

                        is RowComponentEditor    -> {
                            val layouts =
                                remember(layout.rowComponents.all) { mutableStateOf(layout.rowComponents.all.value.filter { it.isLayout() }) }

                            GalleryItem(
                                text = "${layout.title} #${index + 1}",
                                childLayouts = layouts.value,
                                layoutId = layout.id,
                            ) {
                                onExpandChange(false)
                                add(builders[ it ?: layout.id])
                            }
                        }
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
