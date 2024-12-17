package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.kotlinflowts.row.RowComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.ComponentEvent
import com.youssef.kotlinflowts.models.kotlinflowts.Screen
import com.youssef.kotlinflowts.utils.clickableNoIndication
import com.youssef.kotlinflowts.utils.hoverSelect

@Composable
internal fun KfRowComponent(
    editor: RowComponentEditor,
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
    val rowComponents by editor.rowComponents.all.collectAsState()
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Column(
        modifier = Modifier
            .testTag(editor.comp.id)
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

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            this.KfLayoutComposable(
                component = component,
                screen = screen,
                selectedComponentId = selectedComponentId,
                componentEditors = rowComponents,
                onBlur = onBlur,
                onFocus = onFocus,
                onComponentChange = onComponentChange,
                showUnsupportedComponents = showUnsupportedComponents,
                separatorComposable = { Spacer(Modifier.width(8.dp)) },
                select = select,
            )
        }
    }
}


@Composable
internal fun RowScope.KfRowComponent(
    editor: RowComponentEditor,
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
    val rowComponents by editor.rowComponents.all.collectAsState()
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Column(
        modifier = Modifier
            .testTag(editor.comp.id)
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
        KfTitle(editor.title, modifier = Modifier.testTag("${component.id}-title"))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            this.KfLayoutComposable(
                component = component,
                screen = screen,
                selectedComponentId = selectedComponentId,
                componentEditors = rowComponents,
                onBlur = onBlur,
                onFocus = onFocus,
                onComponentChange = onComponentChange,
                showUnsupportedComponents = showUnsupportedComponents,
                separatorComposable = { Spacer(Modifier.width(8.dp)) },
                select = select,
            )
        }
    }
}

