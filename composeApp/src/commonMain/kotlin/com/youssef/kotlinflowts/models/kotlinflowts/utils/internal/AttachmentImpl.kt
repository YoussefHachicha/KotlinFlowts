package com.youssef.kotlinflowts.models.kotlinflowts.utils.internal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.youssef.kotlinflowts.models.kotlinflowts.components.AbstractMappable
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Attachment
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID

internal class AttachmentImpl(wrapped: MutableMap<String, Any?>) : AbstractMappable(wrapped), Attachment {
    override val id get() = wrapped[ID] as String

    override var url by mutableStateOf( wrapped[Attachment::url.name] as String)

    override val fileName get() = wrapped[Attachment::fileName.name] as? String

    override val filePath get() = wrapped[Attachment::filePath.name] as? String

    override val download: String = (wrapped[Attachment::download.name] as? String) ?: url
}