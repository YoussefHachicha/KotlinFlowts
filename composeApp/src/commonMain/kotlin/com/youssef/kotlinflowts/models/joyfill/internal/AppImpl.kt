package com.youssef.kotlinflowts.models.joyfill.internal

import com.youssef.kotlinflowts.models.joyfill.MutableApp
import com.youssef.kotlinflowts.models.joyfill.MutableFile
import com.youssef.kotlinflowts.models.joyfill.components.AbstractMappable
import com.youssef.kotlinflowts.models.joyfill.toApp
import com.youssef.kotlinflowts.models.joyfill.toComponent
import com.youssef.kotlinflowts.models.joyfill.utils.App
import com.youssef.kotlinflowts.models.joyfill.utils.ID
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList

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