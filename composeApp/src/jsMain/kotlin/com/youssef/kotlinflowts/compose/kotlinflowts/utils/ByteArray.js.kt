package com.youssef.kotlinflowts.compose.kotlinflowts.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
//import java.awt.image.BufferedImage
//import java.io.ByteArrayInputStream
//import javax.imageio.ImageIO

internal actual fun ByteArray.toBitmap(): ImageBitmap {
//    val inputStream = ByteArrayInputStream(this)
//    val bufferedImage: BufferedImage = ImageIO.read(inputStream)
//    return bufferedImage.toComposeImageBitmap()
    return ImageBitmap(1, 1)
}