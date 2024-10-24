package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.editor.joyfill.Validation
import com.youssef.kotlinflowts.editor.joyfill.collections.FieldCollection
import com.youssef.kotlinflowts.editor.joyfill.collections.PageCollection
import com.youssef.kotlinflowts.models.joyfill.JoyStage
import com.youssef.kotlinflowts.models.joyfill.Mappable
import com.youssef.kotlinflowts.models.joyfill.View
import com.youssef.kotlinflowts.models.joyfill.utils.Document

interface DocumentEditor : Mappable {

    var stage: JoyStage

    var name: String

    var id: String

    var identifier: String

    val fields: FieldCollection

    val views: List<View>

    val pages: PageCollection

    fun set(key: String, value: Any?)

    fun <R> get(key: String): R

    fun integrity(): Validation

    fun validate(): Validation

    fun toDocument() : Document
}