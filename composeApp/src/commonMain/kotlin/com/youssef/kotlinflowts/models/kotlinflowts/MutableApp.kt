package com.youssef.kotlinflowts.models.kotlinflowts

import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import com.youssef.kotlinflowts.builder.kotlinflowts.LayoutBuilder
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ComponentId

interface MutableApp : App {
    override var name: String
    override val files: MutableList<MutableFile>
    override var components: MutableList<Component>
    override var builders: MutableMap<ComponentId, LayoutBuilder>
    override var cursor: MutableScreen?
    fun set(key: String, value: Any?)
    override fun toMap(): MutableMap<String, Any?>
}