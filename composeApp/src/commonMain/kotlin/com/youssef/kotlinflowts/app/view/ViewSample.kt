package com.youssef.kotlinflowts.app.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youssef.kotlinflowts.compose.kotlinflowts.App
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.AppEditor
import com.youssef.kotlinflowts.models.kotlinflowts.Screen


@Composable
fun ViewSample(
    updateUi: Int,
    editor: AppEditor,
    onChangeScreen: (Screen) -> Unit
) {
    Column(modifier = Modifier.padding(8.dp)) {
        App(
            editor = editor,
            updateUi = updateUi,
            showUnsupportedComponents = true,
            onChangeScreen = onChangeScreen
        )
    }
}

