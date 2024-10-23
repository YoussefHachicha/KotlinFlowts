package com.youssef.kotlinflowts.models.joyfill.utils

import com.youssef.kotlinflowts.models.joyfill.Mappable

interface Attachment : Mappable {
    val id: String
    val url: String
    val fileName: String?
    val filePath: String?
    val download: String
}