package com.youssef.kotlinflowts.models.kotlinflowts.components.table.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.table.ImageColumn
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Attachment
import com.youssef.kotlinflowts.models.kotlinflowts.utils.JsonList
import com.youssef.kotlinflowts.models.kotlinflowts.utils.toAttachment

@PublishedApi
internal class ImageColumnImpl(wrapped: MutableMap<String, Any?>) : AbstractColumn(wrapped), ImageColumn {
    override val value: MutableList<Attachment> = JsonList(wrapped[ImageColumn::value.name]) { it.toAttachment() }
}