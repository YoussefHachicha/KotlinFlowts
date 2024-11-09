package com.youssef.kotlinflowts.compose.kotlinflowts.utils

import android.graphics.Bitmap.CompressFormat
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import java.io.ByteArrayOutputStream

internal actual fun ImageBitmap.toByteArray(): ByteArray {
    val bos = ByteArrayOutputStream()
    asAndroidBitmap().compress(CompressFormat.PNG, 100, bos)
    return bos.toByteArray()
}