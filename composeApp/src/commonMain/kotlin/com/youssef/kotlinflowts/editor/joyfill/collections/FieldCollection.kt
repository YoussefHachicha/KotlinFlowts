package com.youssef.kotlinflowts.editor.joyfill.collections

import com.youssef.kotlinflowts.editor.joyfill.editors.ChartFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.DateFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.DropdownFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.FieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.FileFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ImageFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.MultiSelectFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.NumberFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.SignatureFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TableFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TextAreaFieldEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TextFieldEditor
import com.youssef.kotlinflowts.models.joyfill.Page

interface FieldCollection {

    fun all(): List<FieldEditor>

    fun from(page: String): List<FieldEditor>

    fun from(page: Page): List<FieldEditor>

    fun find(key: String): FieldEditor?

    fun text(key: String): TextFieldEditor?

    fun textarea(key: String): TextAreaFieldEditor?

    fun signature(key: String): SignatureFieldEditor?

    fun number(key: String): NumberFieldEditor?

    fun date(key: String): DateFieldEditor?

    fun dropdown(key: String): DropdownFieldEditor?

    fun select(key: String): MultiSelectFieldEditor?

    fun image(key: String): ImageFieldEditor?

    fun file(key: String): FileFieldEditor?

    fun table(key: String): TableFieldEditor?

    fun chart(key: String): ChartFieldEditor?
}