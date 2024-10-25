package com.youssef.kotlinflowts.models.joyfill.utils

import com.youssef.kotlinflowts.models.joyfill.File
import com.youssef.kotlinflowts.models.joyfill.Mappable
import com.youssef.kotlinflowts.models.joyfill.fields.Component

interface App : Mappable {
    val id: String
    val identifier: String
    val name: String
    val files: List<File>
    val components: List<Component>
    fun <R> get(key: String): R

    fun copy(): App
}