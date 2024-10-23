package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.BlockField

@PublishedApi
internal open class BlockFieldImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedField<String>(wrapped), BlockField