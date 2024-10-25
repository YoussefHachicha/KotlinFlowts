package com.youssef.kotlinflowts.models.joyfill

import com.youssef.kotlinflowts.models.joyfill.fields.Component
import com.youssef.kotlinflowts.models.joyfill.utils.App

interface MutableApp : App {
    override var name: String
    override val files: MutableList<MutableFile>
    override val components: MutableList<Component>
    fun set(key: String, value: Any?)
    override fun toMap(): MutableMap<String, Any?>
}