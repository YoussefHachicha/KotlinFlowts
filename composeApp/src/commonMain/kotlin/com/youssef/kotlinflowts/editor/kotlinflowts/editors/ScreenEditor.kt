package com.youssef.kotlinflowts.editor.kotlinflowts.editors

interface ScreenEditor {
    var id: String

    var name: String

    fun duplicate(): ScreenEditor

    fun delete()
}