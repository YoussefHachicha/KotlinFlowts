package com.youssef.kotlinflowts.editor.joyfill.editors

interface PageEditor {
    var id: String

    var name: String

    fun duplicate(): PageEditor

    fun delete()
}