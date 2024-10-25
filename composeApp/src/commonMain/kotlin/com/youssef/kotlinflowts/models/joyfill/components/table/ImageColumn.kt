package com.youssef.kotlinflowts.models.joyfill.components.table

import com.youssef.kotlinflowts.models.joyfill.utils.Attachment

interface ImageColumn : Column {
    val value: List<Attachment>
}