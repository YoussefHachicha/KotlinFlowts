package com.youssef.kotlinflowts.models.joyfill

interface MutablePage : Page {
    override val positions: MutableList<FieldPosition>
}