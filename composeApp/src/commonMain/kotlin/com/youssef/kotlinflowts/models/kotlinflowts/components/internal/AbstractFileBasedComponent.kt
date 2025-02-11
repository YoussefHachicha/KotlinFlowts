package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.utils.Attachment
import com.youssef.kotlinflowts.models.kotlinflowts.utils.toAttachment

@PublishedApi
internal abstract class AbstractFileBasedComponent(
    wrapped: MutableMap<String, Any?>
) : AbstractListBasedComponent<Attachment>(wrapped) {
    override fun factory(map: MutableMap<String, Any?>): Attachment = map.toAttachment()
}