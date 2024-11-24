package com.youssef.kotlinflowts.editor.kotlinflowts.editors.internal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.events.kotlinflowts.ChangeEvent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

@PublishedApi
internal open class AnyComponentEditor<out F : Component>(
    app: App,
    override val comp: F,
    onChange: ((ChangeEvent) -> Unit)?
) : EventTrigger<F>(app, comp, onChange), ComponentEditor {

    override var id: String
        get() = this.comp.id
        set(value) {}

    override var identifier: String
        get() = this.comp.identifier
        set(value) {}

    override var title: String
        get() = this.comp.title
        set(value) {
            this.comp.title = value
        }

    override var borderColor: Color by mutableStateOf(Color.Red)
    override var padding: Int by mutableIntStateOf(0)

    override val type get() = comp.type
}