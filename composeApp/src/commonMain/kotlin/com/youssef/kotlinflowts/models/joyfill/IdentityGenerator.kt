package com.youssef.kotlinflowts.models.joyfill

import com.youssef.kotlinflowts.models.joyfill.internal.IdentityGeneratorImpl


interface IdentityGenerator {
    fun generate(): String

    companion object {
        val default: IdentityGenerator by lazy { IdentityGeneratorImpl() }
    }
}