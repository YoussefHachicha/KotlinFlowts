package com.youssef.kotlinflowts.models.kotlinflowts.components.table

import com.youssef.kotlinflowts.models.kotlinflowts.utils.Attachment

interface ImageColumn : Column {
    val value: List<Attachment>
}