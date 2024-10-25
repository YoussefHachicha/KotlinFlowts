package com.youssef.kotlinflowts.compose.joyfill

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.youssef.kotlinflowts.models.joyfill.components.core.Component

@Composable
internal fun JoyTitle(
    component: Component,
    modifier: Modifier = Modifier
) = JoyTitle(component.title, modifier)

@Composable
internal fun JoyTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(title, modifier = modifier)
}