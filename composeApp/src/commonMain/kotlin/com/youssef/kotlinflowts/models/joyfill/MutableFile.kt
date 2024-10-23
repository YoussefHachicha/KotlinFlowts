package com.youssef.kotlinflowts.models.joyfill

interface MutableFile : File {
    override var name: String
    override val pages: MutableList<MutablePage>
    override val pageOrder: MutableList<String>
}