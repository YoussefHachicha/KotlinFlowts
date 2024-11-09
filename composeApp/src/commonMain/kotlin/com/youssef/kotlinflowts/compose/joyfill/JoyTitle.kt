package com.youssef.kotlinflowts.compose.joyfill

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.youssef.kotlinflowts.models.joyfill.components.core.Component

@Composable
internal fun KfTitle(
    component: Component,
    modifier: Modifier = Modifier
) = KfTitle(component.title, modifier)

@Composable
internal fun KfTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(title, modifier = modifier)
}