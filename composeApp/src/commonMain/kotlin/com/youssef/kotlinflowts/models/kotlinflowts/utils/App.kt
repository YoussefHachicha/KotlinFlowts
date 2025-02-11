package com.youssef.kotlinflowts.models.kotlinflowts.utils

import com.youssef.kotlinflowts.builder.kotlinflowts.LayoutBuilder
import com.youssef.kotlinflowts.editor.kotlinflowts.editors.ComponentEditor
import com.youssef.kotlinflowts.models.kotlinflowts.File
import com.youssef.kotlinflowts.models.kotlinflowts.Mappable
import com.youssef.kotlinflowts.models.kotlinflowts.Screen
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component

typealias ComponentId = String

interface App : Mappable {
    val id: String
    val identifier: String
    val name: String
    val files: List<File>
    val components: List<Component>
    val builders: Map<ComponentId, LayoutBuilder>
    val cursor: Screen?
    fun <R> get(key: String): R

    fun copy(): App
}