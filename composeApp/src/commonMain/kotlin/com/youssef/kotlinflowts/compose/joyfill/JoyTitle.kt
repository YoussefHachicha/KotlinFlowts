package com.youssef.kotlinflowts.compose.joyfill

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.youssef.kotlinflowts.models.joyfill.fields.Field

@Composable
internal fun JoyTitle(
    field: Field,
    modifier: Modifier = Modifier
) = JoyTitle(field.title, modifier)

@Composable
internal fun JoyTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(title, modifier = modifier)
}