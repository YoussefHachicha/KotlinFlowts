package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.ListBasedComponent
import com.youssef.kotlinflowts.models.joyfill.utils.Attachment

interface FileBasedComponentEditor : ComponentEditor, FileBasedEditor {
    override val component: ListBasedComponent<Attachment>
}