package com.youssef.kotlinflowts.editor.kotlinflowts.editors

import androidx.compose.ui.graphics.Color
import com.youssef.kotlinflowts.editor.kotlinflowts.Validation
import com.youssef.kotlinflowts.editor.kotlinflowts.collections.CompCollection
import com.youssef.kotlinflowts.editor.kotlinflowts.collections.ScreenCollection
import com.youssef.kotlinflowts.models.kotlinflowts.Stage
import com.youssef.kotlinflowts.models.kotlinflowts.Mappable
import com.youssef.kotlinflowts.models.kotlinflowts.View
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ComponentId

interface AppEditor : Mappable {

    var stage: Stage

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

    var selectedEditorComponent: ComponentEditor?
}