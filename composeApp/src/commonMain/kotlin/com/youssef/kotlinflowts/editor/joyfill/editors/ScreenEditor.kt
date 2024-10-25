package com.youssef.kotlinflowts.editor.joyfill.editors

interface ScreenEditor {
    var id: String

    var name: String

    fun duplicate(): ScreenEditor

    fun delete()
}