package com.youssef.kotlinflowts.app.gallery

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component

@Composable
fun ComponentsGallerySample() {
    val componentsTypes = Component.Type.entries
    LazyColumn {
        items(componentsTypes){
            GalleryItem(it.name)
        }
    }
}


