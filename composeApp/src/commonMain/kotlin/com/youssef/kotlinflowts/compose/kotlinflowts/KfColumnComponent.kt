package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.builder.kotlinflowts.LayoutBuilder
import com.youssef.kotlinflowts.editor.kotlinflowts.column.ColumnComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.ComponentEvent
import com.youssef.kotlinflowts.models.kotlinflowts.Screen

@Composable
internal fun KfColumnComponent(
    editor: ColumnComponentEditor,
    screen: Screen,
    builders: MutableMap<String, LayoutBuilder>,
    onBlur: ((event: ComponentEvent) -> Unit)? = null,
    onFocus: ((event: ComponentEvent) -> Unit)? = null,
    onComponentChange: ((event: ComponentEvent) -> Unit)? = null,
    showUnsupportedComponents: Boolean = false,
) {
    val columnBuilder by remember(builders) { mutableStateOf(builders[editor.comp.id]) }
    val component = remember(editor) { editor.comp }
    val columnComponents by remember(editor, columnBuilder?.updateUi) { mutableStateOf(editor.columnComponents.all()) }

    Column(
        modifier = Modifier
            .testTag(editor.comp.id)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(20.dp)
            )
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
            builders = builders,
            onComponentChange = onComponentChange,
            showUnsupportedComponents = showUnsupportedComponents,
            separatorComposable = { Spacer(modifier = Modifier.height(8.dp)) },
        )
    }
}

