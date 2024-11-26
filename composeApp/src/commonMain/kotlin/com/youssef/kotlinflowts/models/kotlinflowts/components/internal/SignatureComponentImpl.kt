package com.youssef.kotlinflowts.models.kotlinflowts.components.internal

import com.youssef.kotlinflowts.models.kotlinflowts.components.SignatureComponent

@PublishedApi
internal class SignatureComponentImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractValueBasedComponent<String>(wrapped), SignatureComponent{

}