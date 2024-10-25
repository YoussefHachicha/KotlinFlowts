package com.youssef.kotlinflowts.models.joyfill.components.table.internal

import com.youssef.kotlinflowts.models.joyfill.components.table.ImageColumn
import com.youssef.kotlinflowts.models.joyfill.utils.Attachment
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList
import com.youssef.kotlinflowts.models.joyfill.utils.toAttachment

@PublishedApi
internal class ImageColumnImpl(wrapped: MutableMap<String, Any?>) : AbstractColumn(wrapped), ImageColumn {
    override val value: MutableList<Attachment> = JsonList(wrapped[ImageColumn::value.name]) { it.toAttachment() }
}