package com.youssef.kotlinflowts.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

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
