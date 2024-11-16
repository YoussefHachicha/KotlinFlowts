package com.youssef.kotlinflowts.app.gallery

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.builder.kotlinflowts.AppBuilder
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component

@Composable
fun ComponentsGallerySample(appBuilder: AppBuilder) {
    val componentsTypes = Component.Type.entries
    val columnComponents = appBuilder.app.components.filter { it.type == Component.Type.column }
    val rowComponents = appBuilder.app.components.filter { it.type == Component.Type.row }
    var expanded by remember { mutableStateOf(false) }
    var selectedComponentType by remember { mutableStateOf(Component.Type.text) }

    LazyColumn {
        items(componentsTypes) { type ->
            GalleryItem(type.name) {
                selectedComponentType = type
                if (columnComponents.isNotEmpty() || rowComponents.isNotEmpty()) {
                    expanded = true
                } else {
                    appBuilder.add(type)
                }
            }
        }
    }

    if (expanded)
    GallerySelector(
        columnComponents = columnComponents,
        rowComponents = rowComponents,
        expanded = expanded,
        builders = appBuilder.app.builders,
        onExpandChange = { expanded = it },
    ) { builder ->
        if (builder == null) {
            appBuilder.add(selectedComponentType)
        } else {
            println("Custom Builder $builder")
            builder.add(selectedComponentType)
        }
    }
}