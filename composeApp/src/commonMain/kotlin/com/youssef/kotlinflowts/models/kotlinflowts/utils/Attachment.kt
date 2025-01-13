package com.youssef.kotlinflowts.models.kotlinflowts.utils

import com.youssef.kotlinflowts.models.kotlinflowts.Mappable

interface Attachment : Mappable {
    val id: String
    var url: String
    val fileName: String?
    val filePath: String?
    val download: String
}