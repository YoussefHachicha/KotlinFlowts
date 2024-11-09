package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import com.youssef.kotlinflowts.compose.kotlinflowts.utils.toBitmap
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import androidx.compose.foundation.Image as FoundationImage

@OptIn(ExperimentalEncodingApi::class)
@Composable
internal fun Image(
    url: String,
    description: String,
    modifier: Modifier = Modifier.fillMaxWidth().heightIn(200.dp, 400.dp)
        .clip(RoundedCornerShape(8.dp))
) {
    if (url.startsWith("data:")) {
        val base64 = url.split("base64,").last()
        val bitmap = Base64.decode(base64).toBitmap()
        FoundationImage(bitmap, "signature", modifier = modifier)
    } else {
        SubcomposeAsyncImage(
            model = url,
            contentDescription = description,
            modifier = modifier,
            loading = {
                CircularProgressIndicator()
            },
            error = {
                Text("Failed to load image: ${it.result.throwable.message}")
            }
        )
    }
}