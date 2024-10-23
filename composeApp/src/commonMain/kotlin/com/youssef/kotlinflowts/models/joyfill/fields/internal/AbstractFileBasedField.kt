package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.utils.Attachment
import com.youssef.kotlinflowts.models.joyfill.utils.toAttachment

@PublishedApi
internal abstract class AbstractFileBasedField(
    wrapped: MutableMap<String, Any?>
) : AbstractListBasedField<Attachment>(wrapped) {
    override fun factory(map: MutableMap<String, Any?>): Attachment = map.toAttachment()
}