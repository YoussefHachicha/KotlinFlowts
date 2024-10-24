package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.utils.Attachment

interface FileBasedEditor {
    val value: List<Attachment>

    fun add(url: String)

    fun add(urls: List<String>)

    fun set(urls: List<String>)

    fun remove(key: String?)

    fun remove(keys: List<String>)
}