package com.youssef.kotlinflowts.models.joyfill

interface MutableScreen : Screen {
    override val positions: MutableList<ComponentPosition>
}