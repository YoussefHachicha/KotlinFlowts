package com.youssef.kotlinflowts.models.kotlinflowts.utils

import com.youssef.kotlinflowts.models.kotlinflowts.utils.internal.AttachmentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.utils.internal.OptionImpl

fun option(
    id: String,
    value: String,
    deleted: Boolean? = null
): Option2 = mutableMapOf<String, Any?>(
    ID to id,
    Option2::value.name to value,
    Option2::deleted.name to deleted
).toOption()

fun MutableMap<String, Any?>.toOption(): Option2 = OptionImpl(this)

fun Attachment(
    id: String,
    url: String,
    fileName: String? = null,
    filePath: String? = null,
    download: String? = null
): Attachment = mutableMapOf<String, Any?>(
    ID to id,
    Attachment::url.name to url,
    Attachment::fileName.name to fileName,
    Attachment::filePath.name to filePath,
    Attachment::download.name to download
).toAttachment()

fun MutableMap<String, Any?>.toAttachment(): Attachment = AttachmentImpl(this)