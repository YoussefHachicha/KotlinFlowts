package com.youssef.kotlinflowts.models.joyfill.internal

import com.youssef.kotlinflowts.models.joyfill.File
import com.youssef.kotlinflowts.models.joyfill.MutableFile
import com.youssef.kotlinflowts.models.joyfill.MutableScreen
import com.youssef.kotlinflowts.models.joyfill.View
import com.youssef.kotlinflowts.models.joyfill.fields.AbstractMappable
import com.youssef.kotlinflowts.models.joyfill.utils.ID
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList

@PublishedApi
internal class FileImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractMappable(wrapped), MutableFile {

    override var id: String
        get() = wrapped[ID] as String
        set(value) {
            wrapped[ID] = value
        }

    override var identifier: String
        get() = wrapped[File::identifier.name] as String
        set(value) {
            wrapped[File::identifier.name] = value
        }

    override var name: String
        get() = wrapped[File::name.name] as String
        set(value) {
            wrapped[File::name.name] = value
        }

    override val screens: MutableList<MutableScreen> = JsonList(wrapped[File::screens.name]) { ScreenImpl(it) }

    override val screenOrder: MutableList<String> = wrapped[File::screenOrder.name] as MutableList<String>

    override val views: List<View> = run {
        val key = File::views.name
        val value = wrapped[key]
        if(value==null) {
            wrapped[key] = mutableListOf<Map<String, Any?>>()
        }
        JsonList(wrapped[key]) { ViewImpl(it) }
    }
}