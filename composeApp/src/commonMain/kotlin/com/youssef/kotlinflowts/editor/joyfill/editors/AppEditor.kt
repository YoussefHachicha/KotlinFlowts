package com.youssef.kotlinflowts.editor.joyfill.editors

import com.youssef.kotlinflowts.editor.joyfill.Validation
import com.youssef.kotlinflowts.editor.joyfill.collections.CompCollection
import com.youssef.kotlinflowts.editor.joyfill.collections.ScreenCollection
import com.youssef.kotlinflowts.models.joyfill.JoyStage
import com.youssef.kotlinflowts.models.joyfill.Mappable
import com.youssef.kotlinflowts.models.joyfill.View
import com.youssef.kotlinflowts.models.joyfill.utils.App

interface AppEditor : Mappable {

    var stage: JoyStage

    var name: String

    var id: String

    var identifier: String

    val components: CompCollection

    val views: List<View>

    val screens: ScreenCollection

    fun set(key: String, value: Any?)

    fun <R> get(key: String): R

    fun integrity(): Validation

    fun validate(): Validation

    fun toApp() : App
}