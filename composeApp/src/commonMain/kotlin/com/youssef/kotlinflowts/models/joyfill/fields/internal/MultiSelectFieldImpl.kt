package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.AbstractField
import com.youssef.kotlinflowts.models.joyfill.fields.DropdownField
import com.youssef.kotlinflowts.models.joyfill.fields.MultiSelectField
import com.youssef.kotlinflowts.models.joyfill.utils.JsonList
import com.youssef.kotlinflowts.models.joyfill.utils.Option2
import com.youssef.kotlinflowts.models.joyfill.utils.toOption

@PublishedApi
internal open class MultiSelectFieldImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractField(wrapped), MultiSelectField {
    override val options: List<Option2> = JsonList(wrapped[DropdownField::options.name]) { it.toOption() }
    override val value: MutableList<String> get() = wrapped[MultiSelectField::value.name] as MutableList<String>
}