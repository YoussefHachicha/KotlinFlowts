package com.youssef.kotlinflowts.editor.kotlinflowts.collections

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ScreenEditor
import com.youssef.kotlinflowts.models.kotlinflowts.Screen

interface ScreenCollection {
    fun raw() : List<Screen>

    fun all(): List<ScreenEditor>
    /**
     * @param key "A Key can be either an id of the page, or the name of the page"
     * @throws IllegalArgumentException if the key is not found
     */
    fun find(key: String): ScreenEditor?
    fun at(index: Int): ScreenEditor?
}