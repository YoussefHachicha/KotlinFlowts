package com.youssef.kotlinflowts.models.joyfill.fields.table

import com.youssef.kotlinflowts.models.joyfill.utils.Attachment

interface ImageColumn : Column {
    val value: List<Attachment>
}