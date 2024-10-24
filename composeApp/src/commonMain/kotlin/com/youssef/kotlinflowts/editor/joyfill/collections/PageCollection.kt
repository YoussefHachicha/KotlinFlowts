package com.youssef.kotlinflowts.editor.joyfill.collections

import com.youssef.kotlinflowts.editor.joyfill.editors.PageEditor
import com.youssef.kotlinflowts.models.joyfill.Page

interface PageCollection {
    fun raw() : List<Page>

    fun all(): List<PageEditor>
    /**
     * @param key "A Key can be either an id of the page, or the name of the page"
     * @throws IllegalArgumentException if the key is not found
     */
    fun find(key: String): PageEditor?
    fun at(index: Int): PageEditor?
}