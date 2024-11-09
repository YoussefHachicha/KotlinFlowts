package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ListBasedComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Attachment

interface FileBasedComponentEditor : ComponentEditor, FileBasedEditor {
    override val comp: ListBasedComponent<Attachment>
}