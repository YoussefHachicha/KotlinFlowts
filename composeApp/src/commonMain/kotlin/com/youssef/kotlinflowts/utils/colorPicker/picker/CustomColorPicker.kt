package colorPicker.picker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Custom Color Picker Component that shows a HSV representation of a color, with a Hue Bar on the right,
 * Alpha Bar on the bottom and the rest of the area covered with an area with saturation value touch area.
 *
 * @param modifier modifiers to set to this color picker.
 * @param color the initial color to set on the picker.
 * @param showAlphaBar whether or not to show the bottom alpha bar on the color picker.
 * @param onColorChanged callback that is triggered when the color changes
 *
 */
@Composable
fun CustomColorPicker(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    showAlphaBar: Boolean = true,
    onColorChanged: (HsvColor) -> Unit
) {
    ClassicColorPicker(
        modifier = modifier,
        color = HsvColor.from(color),
        showAlphaBar = showAlphaBar,
        onColorChanged = onColorChanged
    )
}

/**
 * Classic Color Picker Component that shows a HSV representation of a color, with a Hue Bar on the right,
 * Alpha Bar on the bottom and the rest of the area covered with an area with saturation value touch area.
 *
 * @param modifier modifiers to set to this color picker.
 * @param color the initial color to set on the picker.
 * @param showAlphaBar whether or not to show the bottom alpha bar on the color picker.
 * @param onColorChanged callback that is triggered when the color changes
 *
 */
@Composable
fun ClassicColorPicker(
    modifier: Modifier = Modifier,
    color: HsvColor = HsvColor.from(Color.Red),
    showAlphaBar: Boolean = true,
    onColorChanged: (HsvColor) -> Unit
) {
    val colorPickerValueState = rememberSaveable(stateSaver = HsvColor.Saver) {
        mutableStateOf(color)
    }
    val barThickness = 16.dp
    val spacingingBetweenBars = 12.dp
    val updatedOnColorChanged by rememberUpdatedState(onColorChanged)
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacingingBetweenBars),
    ) {
        SaturationValueArea(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .width(220.dp)
                .height(180.dp)
                .clip(shape = RoundedCornerShape(4.dp)),
            currentColor = colorPickerValueState.value,
            onSaturationValueChanged = { saturation, value ->
                colorPickerValueState.value = colorPickerValueState.value.copy(saturation = saturation, value = value)
                updatedOnColorChanged(colorPickerValueState.value)
            }
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(spacingingBetweenBars),
        ) {

            if (showAlphaBar)
                AlphaBar(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(16.dp))
                        .height(barThickness),
                    currentColor = colorPickerValueState.value,
                    onAlphaChanged = { alpha ->
                        colorPickerValueState.value = colorPickerValueState.value.copy(alpha = alpha)
                        updatedOnColorChanged(colorPickerValueState.value)
                    }
                )
            HueBar(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .height(barThickness),
                currentColor = colorPickerValueState.value,
                onHueChanged = { newHue ->
                    colorPickerValueState.value = colorPickerValueState.value.copy(hue = newHue)
                    updatedOnColorChanged(colorPickerValueState.value)
                }
            )
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            val currentColor = colorPickerValueState.value
            val red = currentColor.toColor().red.toString()
            val green = currentColor.toColor().green.toString()
            val blue = currentColor.toColor().blue.toString()
            Column {
                Text(
                    text = "HEX",
                )
                Text(
                    text = currentColor.toHexCode(),
                    color = Color.Black
                )
            }
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceAround
//            ){
//                Column {
//                    Text(
//                        text = "R",
//                    )
//                    Text(
//                        text = "${red.subSequence(2, red.lastIndex)}",
//                    )
//
//                }
//                Column {
//                    Text(
//                        text = "G",
//                    )
//                    Text(
//                        text = "${green.subSequence(2, green.lastIndex)}",
//                    )
//
//                }
//                Column {
//                    Text(
//                        text = "B",
//                    )
//                    Text(
//                        text = "${blue.subSequence(2, blue.lastIndex)}",
//                    )
//                }
//            }
        }
    }
}
