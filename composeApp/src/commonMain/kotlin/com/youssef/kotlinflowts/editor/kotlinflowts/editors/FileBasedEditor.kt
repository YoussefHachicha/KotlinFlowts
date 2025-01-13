package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.utils.Attachment

interface FileBasedEditor {
    val fileValue: List<Attachment>

    fun add(url: String)

    fun add(urls: List<String>)

    fun set(urls: List<String>)

    fun remove(key: String?)

    fun remove(keys: List<String>)

    fun clear()
}