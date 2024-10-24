package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.models.joyfill.fields.ListBasedField
import com.youssef.kotlinflowts.models.joyfill.utils.Attachment

interface FileBasedFieldEditor : FieldEditor, FileBasedEditor {
    override val field: ListBasedField<Attachment>
}