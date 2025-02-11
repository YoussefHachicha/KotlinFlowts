package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.column.ColumnComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.ComponentEvent
import com.youssef.kotlinflowts.models.kotlinflowts.Screen
import com.youssef.kotlinflowts.utils.clickableNoIndication

@Composable
internal fun KfColumnComponent(
    editor: ColumnComponentEditor,
    screen: Screen,
    selectedComponentId: String,
    isSelected: Boolean,
    onBlur: ((event: ComponentEvent) -> Unit)? = null,
    onFocus: ((event: ComponentEvent) -> Unit)? = null,
    onComponentChange: ((event: ComponentEvent) -> Unit)? = null,
    showUnsupportedComponents: Boolean = false,
    select: (ComponentEditor) -> Unit,
) {
    val component = remember(editor) { editor.comp }
    val columnComponents by derivedStateOf {
        editor.columnComponents.all.toList()
    }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isSelected || isHovered) Color.Blue else editor.borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .hoverable(interactionSource = interactionSource)
            .clickableNoIndication(onClick = { select(editor) })
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        if (!editor.disableTitle)
            KfTitle(editor.title, modifier = Modifier.testTag("${component.id}-title"))

        this.KfLayoutComposable(
            componentEditors = columnComponents,
            screen = screen,
            onBlur = onBlur,
            selectedComponentId = selectedComponentId,
            onFocus = onFocus,
            onComponentChange = onComponentChange,
            showUnsupportedComponents = showUnsupportedComponents,
            separatorComposable = { Spacer(modifier = Modifier.height(8.dp)) },
            select = select
        )
    }
}

@Composable
internal fun RowScope.KfColumnComponent(
    editor: ColumnComponentEditor,
    screen: Screen,
    selectedComponentId: String,
    isSelected: Boolean,
    onBlur: ((event: ComponentEvent) -> Unit)? = null,
    onFocus: ((event: ComponentEvent) -> Unit)? = null,
    onComponentChange: ((event: ComponentEvent) -> Unit)? = null,
    showUnsupportedComponents: Boolean = false,
    select: (ComponentEditor) -> Unit,
) {
    val component = remember(editor) { editor.comp }
    val columnComponents by derivedStateOf {
        editor.columnComponents.all.toList()
    }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .border(
                width = 1.dp,
                color = if (isSelected || isHovered) Color.Blue else editor.borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .hoverable(interactionSource = interactionSource)
            .clickableNoIndication(onClick = { select(editor) })
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        if (!editor.disableTitle)
            KfTitle(editor.title, modifier = Modifier.testTag("${component.id}-title"), 2)

        this.KfLayoutComposable(
            componentEditors = columnComponents,
            screen = screen,
            onBlur = onBlur,
            selectedComponentId = selectedComponentId,
            onFocus = onFocus,
            onComponentChange = onComponentChange,
            showUnsupportedComponents = showUnsupportedComponents,
            separatorComposable = { Spacer(modifier = Modifier.height(8.dp)) },
            select = select
        )
    }
}