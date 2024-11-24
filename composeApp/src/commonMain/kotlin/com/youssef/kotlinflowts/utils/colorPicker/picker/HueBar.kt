package colorPicker.picker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

/**
 * Hue side bar Component that invokes onHueChanged when the value is mutated.
 *
 * @param modifier modifiers to set to the hue bar.
 * @param currentColor the initial color to set on the hue bar.
 * @param onHueChanged the callback that is invoked when hue value changes. Hue is between 0 - 360.
 */
@Composable
internal fun HueBar(
    modifier: Modifier = Modifier,
    currentColor: HsvColor,
    onHueChanged: (Float) -> Unit
) {
    val rainbowBrush = remember {
        Brush.horizontalGradient(getRainbowColors())
    }
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        val down = awaitFirstDown()
                        onHueChanged(
                            getHueFromPoint(
                                down.position.x,
                                this.size.width.toFloat()
                            )
                        )
                        drag(down.id) { change ->
                            change.consumePositionChange()
                            onHueChanged(
                                getHueFromPoint(
                                    change.position.x,
                                    this.size.width.toFloat()
                                )
                            )
                        }
                    }
                }
            }
    ) {
        drawRect(rainbowBrush)
        drawRect(Color.Gray, style = Stroke(0.5.dp.toPx()))

        val huePoint = getPointFromHue(
            color = currentColor,
            width = this.size.width
        )
        drawHorizontalSelector(huePoint)
    }
}

private fun getRainbowColors(): List<Color> {
    return listOf(
        Color(0xFFFF0040),
        Color(0xFFFF00FF),
        Color(0xFF8000FF),
        Color(0xFF0000FF),
        Color(0xFF0080FF),
        Color(0xFF00FFFF),
        Color(0xFF00FF80),
        Color(0xFF00FF00),
        Color(0xFF80FF00),
        Color(0xFFFFFF00),
        Color(0xFFFF8000),
        Color(0xFFFF0000)
    )
}


private fun getPointFromHue(color: HsvColor, width: Float): Float {
    val normalizedHue = color.hue / 360f
    val invertedHue = 1f - normalizedHue
    return width * invertedHue
}
private fun getHueFromPoint(x: Float, width: Float): Float {
    val newX = x.coerceIn(0f, width)
    return 360f * (1f - (newX / width))
}