package com.youssef.kotlinflowts.editor.kotlinflowts.internal

import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ScreenEditor
import com.youssef.kotlinflowts.models.kotlinflowts.Screen
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

internal class ScreenEditorImpl(
    val app: App,
    val screen: Screen
) : ScreenEditor {
    override var id: String
        get() = screen.id
        set(value) {
            TODO()
//            page.id = value
        }

    override var name: String
        get() = screen.name
        set(value) {
            TODO()
//            page.name = value
        }

    override fun duplicate(): ScreenEditor {
        TODO("Not yet implemented")
    }

    override fun delete() {
        TODO("Not yet implemented")
    }
}