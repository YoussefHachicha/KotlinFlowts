package com.youssef.kotlinflowts.models.kotlinflowts.internal

import com.youssef.kotlinflowts.models.kotlinflowts.File
import com.youssef.kotlinflowts.models.kotlinflowts.MutableFile
import com.youssef.kotlinflowts.models.kotlinflowts.MutableScreen
import com.youssef.kotlinflowts.models.kotlinflowts.View
import com.youssef.kotlinflowts.models.kotlinflowts.components.AbstractMappable
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID
import com.youssef.kotlinflowts.models.kotlinflowts.utils.JsonList

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