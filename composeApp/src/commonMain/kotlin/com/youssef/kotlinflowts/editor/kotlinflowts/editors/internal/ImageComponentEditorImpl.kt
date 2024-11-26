package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

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
            Text(
                text = "Image",
            )
        """.trimIndent()
    }
}