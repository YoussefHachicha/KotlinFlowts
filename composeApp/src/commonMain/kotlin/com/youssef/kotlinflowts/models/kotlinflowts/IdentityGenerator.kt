package com.youssef.kotlinflowts.models.kotlinflowts

import com.youssef.kotlinflowts.models.kotlinflowts.internal.IdentityGeneratorImpl


interface IdentityGenerator {
    fun generate(): String

    companion object {
        val default: IdentityGenerator by lazy { IdentityGeneratorImpl() }
    }
}