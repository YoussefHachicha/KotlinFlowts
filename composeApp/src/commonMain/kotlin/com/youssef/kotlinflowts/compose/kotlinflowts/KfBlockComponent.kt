package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.youssef.kotlinflowts.models.kotlinflowts.ComponentPosition
import com.youssef.kotlinflowts.models.kotlinflowts.components.BlockComponent

@Composable
internal fun KfBlockComponent(
    component: BlockComponent,
    position: ComponentPosition?
) = Column(modifier = Modifier.testTag(component.id).fillMaxWidth()) {
    KfBlockComponentImpl(component, position)
}

@Composable
internal fun ColumnScope.KfBlockComponent(
    component: BlockComponent,
    position: ComponentPosition?
) = Column(modifier = Modifier.testTag(component.id).fillMaxWidth()) {
    KfBlockComponentImpl(component, position)
}

@Composable
internal fun RowScope.KfBlockComponent(
    component: BlockComponent,
    position: ComponentPosition?
) = Column(modifier = Modifier.testTag(component.id).weight(1f)) {
    KfBlockComponentImpl(component, position)
}

@Composable
private fun KfBlockComponentImpl(
    component: BlockComponent,
    position: ComponentPosition?
) {
    val color = position?.fontColor?.toColor() ?: Color.Unspecified
    val weight = position?.fontWeight?.toFontWeight()
    val size = position?.fontSize?.sp ?: 12.sp
    val value = component.value?.apply(position?.textTransform) ?: ""
    val elipsis = position?.textOverflow == "elipsis"
    val align = position?.textAlign.toAlign()
    Text(
        text = value,
        color = color,
        fontWeight = weight,
        fontSize = size,
        lineHeight = size,
        textAlign = align,
        maxLines = if (elipsis) 1 else Int.MAX_VALUE,
        overflow = if (elipsis) TextOverflow.Ellipsis else TextOverflow.Clip,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))
}

private fun String.toColor(): Color? {
    val num = replaceFirst("#", "").chunked(2)
    val r = num[0].toIntOrNull(16) ?: return null
    val g = num[1].toIntOrNull(16) ?: return null
    val b = num[2].toIntOrNull(16) ?: return null
    return Color(r, g, b)
}

private fun String.toFontWeight(): FontWeight? = when (lowercase()) {
    "bold" -> FontWeight.Bold
    else   -> null //FontWeight.Normal
}

private fun String.apply(transform: String?) = when (transform?.lowercase()) {
    "uppercase" -> uppercase()
    "lowercase" -> lowercase()
    else        -> this
}

private fun String?.toAlign(): TextAlign = when (this?.lowercase()) {
    "center" -> TextAlign.Center
    "right"  -> TextAlign.Right
    else     -> TextAlign.Left
}