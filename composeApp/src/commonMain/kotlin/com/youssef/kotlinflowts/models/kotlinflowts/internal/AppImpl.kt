package com.youssef.kotlinflowts.models.kotlinflowts.internal

import com.youssef.kotlinflowts.models.kotlinflowts.MutableApp
import com.youssef.kotlinflowts.models.kotlinflowts.MutableFile
import com.youssef.kotlinflowts.models.kotlinflowts.components.AbstractMappable
import com.youssef.kotlinflowts.models.kotlinflowts.toApp
import com.youssef.kotlinflowts.models.kotlinflowts.toComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID
import com.youssef.kotlinflowts.models.kotlinflowts.utils.JsonList

@PublishedApi
internal class AppImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractMappable(wrapped), MutableApp {
    override var name: String
        get() = wrapped[App::name.name] as String
        set(value) {
            wrapped[App::name.name] = value
        }

    override val id: String get() = wrapped[ID] as String

    override val identifier: String get() = wrapped[App::identifier.name] as String

    override val files: MutableList<MutableFile> = JsonList(wrapped[App::files.name]) { FileImpl(it) }

    override val components = JsonList(wrapped[App::components.name]) { it.toComponent() }

    override fun <R> get(key: String): R = wrapped[key] as R

    override fun set(key: String, value: Any?) {
        wrapped[key] = value
    }

    override fun copy(): App  = toJsonString().toApp()
}