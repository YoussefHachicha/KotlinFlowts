package com.youssef.kotlinflowts.compose.joyfill.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toAwtImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

internal actual fun ImageBitmap.toByteArray(): ByteArray {
    val bos = ByteArrayOutputStream()
    val bufferedImage = this.toAwtImage()
    ImageIO.write(bufferedImage, "png", bos)
    return bos.toByteArray()
}