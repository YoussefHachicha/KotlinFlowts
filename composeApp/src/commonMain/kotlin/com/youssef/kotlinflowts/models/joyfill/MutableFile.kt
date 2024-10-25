package com.youssef.kotlinflowts.models.joyfill

interface MutableFile : File {
    override var name: String
    override val screens: MutableList<MutableScreen>
    override val screenOrder: MutableList<String>
}