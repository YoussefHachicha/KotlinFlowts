package com.youssef.kotlinflowts.models.kotlinflowts

interface MutableScreen : Screen {
    override val positions: MutableList<ComponentPosition>
}