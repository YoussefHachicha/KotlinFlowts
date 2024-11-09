package com.youssef.kotlinflowts.models.kotlinflowts

import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

interface MutableApp : App {
    override var name: String
    override val files: MutableList<MutableFile>
    override val components: MutableList<Component>
    fun set(key: String, value: Any?)
    override fun toMap(): MutableMap<String, Any?>
}