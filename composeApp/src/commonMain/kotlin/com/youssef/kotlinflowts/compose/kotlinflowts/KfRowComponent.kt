package com.youssef.kotlinflowts.compose.kotlinflowts

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.editor.kotlinflowts.row.RowComponentEditor
import com.youssef.kotlinflowts.manager.kotlinflowts.ComponentEvent
import com.youssef.kotlinflowts.models.kotlinflowts.Screen

@Composable
internal fun KfRowComponent(
    editor: RowComponentEditor,
    screen: Screen,
    onBlur: ((event: ComponentEvent) -> Unit)? = null,
    onFocus: ((event: ComponentEvent) -> Unit)? = null,
    onComponentChange: ((event: ComponentEvent) -> Unit)? = null,
    showUnsupportedComponents: Boolean = false,
) {
    val component = remember(editor) { editor.comp }
    val rowComponents = remember(editor) { editor.rowComponents.all() }

    Column(
        modifier = Modifier
            .testTag(editor.comp.id)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        KfTitle(component.title, modifier = Modifier.testTag("${component.id}-title"))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            KfLayoutComposable(
                componentEditors = rowComponents,
                component = component,
                screen = screen,
                onBlur = onBlur,
                onFocus = onFocus,
                onComponentChange = onComponentChange,
                showUnsupportedComponents = showUnsupportedComponents,
                separatorComposable = { Spacer(Modifier.width(8.dp)) }
            )
        }
    }

}