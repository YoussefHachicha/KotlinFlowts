package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor

@Composable
internal fun ColumnScope.KfTitle(
    title: String,
    modifier: Modifier = Modifier,
    bottomPadding: Int = 2
) = Text(title, modifier = modifier.padding(bottom = bottomPadding.dp))

@Composable
internal fun RowScope.KfTitle(
    title: String,
    modifier: Modifier = Modifier,
    bottomPadding: Int = 2
) = Text(title, modifier = modifier.padding(bottom = bottomPadding.dp))

@Composable
internal fun KfTitle(
    title: String,
    modifier: Modifier = Modifier,
    bottomPadding: Int = 2
) = Text(title, modifier = modifier.padding(bottom = bottomPadding.dp))
