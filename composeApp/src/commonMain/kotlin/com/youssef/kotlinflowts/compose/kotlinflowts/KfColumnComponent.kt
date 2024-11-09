package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.column.ColumnComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.ComponentEvent
import com.youssef.kotlinflowts.models.kotlinflowts.Screen

@Composable
internal fun KfColumnComponent(
    editor: ColumnComponentEditor,
    screen: Screen,
    onBlur: ((event: ComponentEvent) -> Unit)? = null,
    onFocus: ((event: ComponentEvent) -> Unit)? = null,
    onComponentChange: ((event: ComponentEvent) -> Unit)? = null,
    showUnsupportedComponents: Boolean = false,
) {
    val component = remember(editor) { editor.comp }
    val columnComponents = remember(editor) { editor.columnComponents.all() }
    Column(
        modifier = Modifier
            .testTag(editor.comp.id)
            .fillMaxWidth()
            .border(1.dp, Color.Gray)
            .padding(horizontal = 16.dp)
    ) {
        KfTitle(component.title, modifier = Modifier.testTag("${component.id}-title"))
        Spacer(modifier = Modifier.height(2.dp))

        KfLayoutComposable(
            componentEditors = columnComponents,
            component = component,
            screen = screen,
            onBlur = onBlur,
            onFocus = onFocus,
            onComponentChange = onComponentChange,
            showUnsupportedComponents = showUnsupportedComponents,
            separatorComposable = { Spacer(modifier = Modifier.height(8.dp)) }
        )
    }
}

