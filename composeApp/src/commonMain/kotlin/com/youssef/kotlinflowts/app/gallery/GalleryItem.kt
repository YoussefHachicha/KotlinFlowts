package com.youssef.kotlinflowts.app.gallery

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.compose.kotlinflowts.Image

@Composable
fun GalleryItem(
    text: String,
    image: String? = null,
    onClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                onClick()
            }
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) {
        image?.let {
            Image(
                url = it,
                description = text,
                modifier = Modifier.clip(RoundedCornerShape(8.dp))
            )
        }
        Text(text)
    }
}