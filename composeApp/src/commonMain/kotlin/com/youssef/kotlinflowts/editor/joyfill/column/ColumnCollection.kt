package com.youssef.kotlinflowts.editor.joyfill.column

import com.youssef.kotlinflowts.editor.joyfill.editors.ComponentEditor

interface ColumnCollection {
    fun all(): List<ComponentEditor>

    fun find(key: String): ComponentEditor?
}