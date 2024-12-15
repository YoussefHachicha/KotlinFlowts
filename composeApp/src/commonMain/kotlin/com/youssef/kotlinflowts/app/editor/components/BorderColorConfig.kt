package com.youssef.kotlinflowts.app.editor.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.utils.colorPicker.ColorConfig

@Composable
internal fun BorderColorConfig(
    compEditor: ComponentEditor,
    isCollapsed: Boolean
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Border Color:")
        ColorConfig(
            selectedColor = compEditor.borderColor,
            onColorChanged = { compEditor.changeBorderColor(it) },
            isCollapsed = isCollapsed,
            isRow = isCollapsed
        )
    }
}