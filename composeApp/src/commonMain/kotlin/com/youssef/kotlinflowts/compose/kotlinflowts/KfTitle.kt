package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component

@Composable
internal fun KfTitle(
    component: ComponentEditor,
    modifier: Modifier = Modifier
) = KfTitle(component.title, modifier)

@Composable
internal fun ColumnScope.KfTitle(
    component: ComponentEditor,
    modifier: Modifier = Modifier
) = KfTitle(component.title, modifier)

@Composable
internal fun RowScope.KfTitle(
    component: ComponentEditor,
    modifier: Modifier = Modifier
) = KfTitle(component.title, modifier)

@Composable
internal fun KfTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(title, modifier = modifier)
}