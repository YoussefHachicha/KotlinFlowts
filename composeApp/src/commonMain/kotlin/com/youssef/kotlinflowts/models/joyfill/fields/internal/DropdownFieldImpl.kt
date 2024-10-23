package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.DropdownField
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList
import com.youssef.kotlinflowts.models.joyfill.utils.Option2
import com.youssef.kotlinflowts.models.joyfill.utils.toOption

@PublishedApi
internal open class DropdownFieldImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedField<String>(wrapped), DropdownField {
    override val options: List<Option2> = JsonList(wrapped[DropdownField::options.name]) { it.toOption() }
}