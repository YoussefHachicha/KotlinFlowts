package com.youssef.kotlinflowts.editor.joyfill.internal

import com.youssef.kotlinflowts.editor.joyfill.editors.PageEditor
import com.youssef.kotlinflowts.models.joyfill.Page
import com.youssef.kotlinflowts.models.joyfill.utils.Document

internal class PageEditorImpl(
    val document: Document,
    val page: Page
) : PageEditor {
    override var id: String
        get() = page.id
        set(value) {
            TODO()
//            page.id = value
        }

    override var name: String
        get() = page.name
        set(value) {
            TODO()
//            page.name = value
        }

    override fun duplicate(): PageEditor {
        TODO("Not yet implemented")
    }

    override fun delete() {
        TODO("Not yet implemented")
    }
}