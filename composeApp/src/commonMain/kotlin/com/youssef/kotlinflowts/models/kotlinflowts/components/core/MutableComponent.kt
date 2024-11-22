package com.youssef.kotlinflowts.models.kotlinflowts.components.core

interface MutableComponent : Component {
    override var disabled: Boolean
    override var title: String
}