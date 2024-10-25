package com.youssef.kotlinflowts.editor.joyfill.collections

import com.youssef.kotlinflowts.editor.joyfill.editors.ChartComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.DateComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.DropdownComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.FileComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.ImageComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.MultiSelectComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.NumberComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.SignatureComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TableComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TextAreaComponentEditor
import com.youssef.kotlinflowts.editor.joyfill.editors.TextComponentEditor
import com.youssef.kotlinflowts.models.joyfill.Screen

interface CompCollection {

    fun all(): List<ComponentEditor>

    fun from(screen: String): List<ComponentEditor>

    fun from(screen: Screen): List<ComponentEditor>

    fun find(key: String): ComponentEditor?

    fun text(key: String): TextComponentEditor?

    fun textarea(key: String): TextAreaComponentEditor?

    fun signature(key: String): SignatureComponentEditor?

    fun number(key: String): NumberComponentEditor?

    fun date(key: String): DateComponentEditor?

    fun dropdown(key: String): DropdownComponentEditor?

    fun select(key: String): MultiSelectComponentEditor?

    fun image(key: String): ImageComponentEditor?

    fun file(key: String): FileComponentEditor?

    fun table(key: String): TableComponentEditor?

    fun chart(key: String): ChartComponentEditor?
}