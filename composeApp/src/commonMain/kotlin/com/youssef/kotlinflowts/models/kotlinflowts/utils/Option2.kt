package com.youssef.kotlinflowts.models.kotlinflowts.utils

import com.youssef.kotlinflowts.models.kotlinflowts.Mappable

interface Option2 : Mappable {
    val id: String
    val deleted: Boolean
    val value: String
}