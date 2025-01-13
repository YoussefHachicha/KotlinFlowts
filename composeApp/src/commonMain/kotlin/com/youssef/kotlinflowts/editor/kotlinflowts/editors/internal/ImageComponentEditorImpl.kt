package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ImageComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.ImageComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal class ImageComponentEditorImpl(
    app: App,
    override val comp: ImageComponent,
    identity: IdentityGenerator,
    onChange: ((ChangeEvent) -> Unit)?
) : AbstractFileBasedComponentEditor(app, comp, identity, onChange), ImageComponentEditor {
    override fun generateCode(): String {
        return """
            SubcomposeAsyncImage(
                model = "${fileValue.firstOrNull()?.url}",
                contentDescription = null,
                modifier = Modifier,
                loading = {
                    CircularProgressIndicator()
                },
                error = {
                    Text("Failed to load image")
                }
            )
        """.trimIndent()
    }
}
