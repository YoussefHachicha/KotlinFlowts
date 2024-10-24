package com.youssef.kotlinflowts.editor.joyfill.internal

import com.youssef.kotlinflowts.editor.joyfill.collections.PageCollection
import com.youssef.kotlinflowts.editor.joyfill.editors.PageEditor
import com.youssef.kotlinflowts.models.joyfill.Page
import com.youssef.kotlinflowts.models.joyfill.utils.Document

internal class PageCollectionImpl(
    private val document: Document
) : PageCollection {

    override fun raw(): List<Page> = document.files.flatMap { it.pages }

    override fun all() = document.files.flatMap { it.pages }.map { PageEditorImpl(document, it) }

    override fun find(key: String): PageEditor? {
        val page = document.files.flatMap { it.pages }.find {
            it.id == key || it.name == key
        } ?: return null
        return PageEditorImpl(document, page)
    }

    override fun at(index: Int): PageEditor? {
        if (index < 0) return null
        val pages = document.files.flatMap { it.pages }
        if (index >= pages.size) return null
        return PageEditorImpl(document, pages[index])
    }
}