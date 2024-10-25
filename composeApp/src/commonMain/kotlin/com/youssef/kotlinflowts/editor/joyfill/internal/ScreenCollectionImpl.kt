package com.youssef.kotlinflowts.editor.joyfill.internal

import com.youssef.kotlinflowts.editor.joyfill.collections.ScreenCollection
import com.youssef.kotlinflowts.editor.joyfill.editors.ScreenEditor
import com.youssef.kotlinflowts.models.joyfill.Screen
import com.youssef.kotlinflowts.models.joyfill.utils.App

internal class ScreenCollectionImpl(
    private val app: App
) : ScreenCollection {

    override fun raw(): List<Screen> = app.files.flatMap { it.screens }

    override fun all() = app.files.flatMap { it.screens }.map { ScreenEditorImpl(app, it) }

    override fun find(key: String): ScreenEditor? {
        val page = app.files.flatMap { it.screens }.find {
            it.id == key || it.name == key
        } ?: return null
        return ScreenEditorImpl(app, page)
    }

    override fun at(index: Int): ScreenEditor? {
        if (index < 0) return null
        val pages = app.files.flatMap { it.screens }
        if (index >= pages.size) return null
        return ScreenEditorImpl(app, pages[index])
    }
}