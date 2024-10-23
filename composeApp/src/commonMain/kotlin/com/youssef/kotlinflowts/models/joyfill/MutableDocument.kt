package com.youssef.kotlinflowts.models.joyfill

import com.youssef.kotlinflowts.models.joyfill.fields.Field

interface MutableDocument : Document {
    override var name: String
    override val files: MutableList<MutableFile>
    override val fields: MutableList<Field>
    fun set(key: String, value: Any?)
    override fun toMap(): MutableMap<String, Any?>
}