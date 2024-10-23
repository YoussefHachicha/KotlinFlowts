package com.youssef.kotlinflowts.models.joyfill.utils.internal

import com.youssef.kotlinflowts.models.joyfill.fields.AbstractMappable
import com.youssef.kotlinflowts.models.joyfill.utils.Attachment
import com.youssef.kotlinflowts.models.joyfill.utils.ID

internal class AttachmentImpl(wrapped: MutableMap<String, Any?>) : AbstractMappable(wrapped), Attachment {
    override val id get() = wrapped[ID] as String

    override val url get() = wrapped[Attachment::url.name] as String

    override val fileName get() = wrapped[Attachment::fileName.name] as? String

    override val filePath get() = wrapped[Attachment::filePath.name] as? String

    override val download: String = (wrapped[Attachment::download.name] as? String) ?: url
}