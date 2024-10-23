package com.youssef.kotlinflowts.models.joyfill.utils

import com.youssef.kotlinflowts.models.joyfill.Mappable

interface Option2 : Mappable {
    val id: String
    val deleted: Boolean
    val value: String
}