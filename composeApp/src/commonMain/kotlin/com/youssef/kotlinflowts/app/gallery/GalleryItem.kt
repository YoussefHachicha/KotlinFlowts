package com.youssef.kotlinflowts.app.gallery

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.builder.kotlinflowts.LayoutBuilder
import com.youssef.kotlinflowts.compose.kotlinflowts.Image
import com.youssef.kotlinflowts.editor.kotlinflowts.column.ColumnComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.row.RowComponentEditor

@Composable
fun GalleryItem(
    text: String,
    layoutId: String = "",
    childLayouts: List<ComponentEditor> = emptyList(),
    image: String? = null,
    onClick: (layoutId: String?) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 1.dp, horizontal = 8.dp)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    if (childLayouts.isEmpty()) {
                        onClick(layoutId)
                    } else {
                        isExpanded = !isExpanded
                    }
                }
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(text)
                image?.let {
                    Image(
                        url = it,
                        description = text,
                        modifier = Modifier.clip(RoundedCornerShape(8.dp))
                    )
                }
            }
            if (childLayouts.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "Add",
                        tint = Color.Gray,
                        modifier = Modifier
                            .clickable { onClick(layoutId) }
                    )
                    Icon(
                        imageVector = if (isExpanded) Icons.Outlined.ArrowDropUp else Icons.Outlined.ArrowDropDown,
                        contentDescription = if (isExpanded) "Collapse" else "Expand",
                        tint = Color.Gray,
                        modifier = Modifier
                            .clickable { isExpanded = !isExpanded }
                    )
                }
            }
        }

        // Dropdown content
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 24.dp) // Indent nested items
            ) {
                childLayouts.forEachIndexed { index, layout ->
                    when (layout) {
                        is ColumnComponentEditor -> {
                            val layouts = remember(layout.columnComponents.all) {
                                mutableStateOf(layout.columnComponents.all.value.filter { it.isLayout() })
                            }

                            GalleryItem(
                                text = "${layout.title} #${index + 1}",
                                childLayouts = layouts.value,
                                layoutId = layout.id,
                            ) {
                                onClick(layout.id)
                            }
                        }

                        is RowComponentEditor    -> {
                            val layouts = remember(layout.rowComponents.all) {
                                mutableStateOf(layout.rowComponents.all.value.filter { it.isLayout() })
                            }

                            GalleryItem(
                                text = "${layout.title} #${index + 1}",
                                childLayouts = layouts.value,
                                layoutId = layout.id,
                            ) {
                                onClick(layout.id)
                            }
                        }
                    }
                }
            }
        }
    }
}