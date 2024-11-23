package com.youssef.kotlinflowts.utils

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.clickableNoIndication(
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit,
): Modifier {
    return this.clickable(
        interactionSource = interactionSource,
        onClick = onClick,
        role = null,
        indication = null,
    )
}


fun Modifier.thenIf(
    condition: Boolean,
    otherModifier: @Composable Modifier.() -> Modifier,
): Modifier =
    composed {
        // Cannot use Modifier#then() because it stacks the previous modifiers multiple times
        if (condition) {
            this.otherModifier()
        } else {
            this
        }
    }


fun Modifier.hoverSelect(
    isSelected: Boolean,
    onSelect: () -> Unit,
): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    this
        .hoverable(interactionSource = interactionSource)
        .fillMaxWidth()
        .clickableNoIndication(onClick = onSelect)
        .thenIf(
            isSelected || isHovered
        ) {
            border(
                width = 1.dp,
                color = Color.Blue
            )
        }
}