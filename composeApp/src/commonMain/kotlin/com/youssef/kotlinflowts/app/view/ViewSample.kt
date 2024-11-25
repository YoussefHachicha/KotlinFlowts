package com.youssef.kotlinflowts.app.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.compose.kotlinflowts.App
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.AppEditor
import com.youssef.kotlinflowts.models.kotlinflowts.Screen


@Composable
fun ViewSample(
    updateUi: Int,
    editor: AppEditor,
    currentScreen: Screen,
    onChangeScreen: (Screen) -> Unit
) {
    Column(modifier = Modifier.padding(8.dp)) {
        App(
            editor = editor,
            updateUi = updateUi,
            currentScreen = currentScreen,
            showUnsupportedComponents = true,
            onChangeScreen = onChangeScreen
        )
    }
}

