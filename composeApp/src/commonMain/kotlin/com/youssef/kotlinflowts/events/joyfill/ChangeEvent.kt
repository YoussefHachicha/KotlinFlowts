package com.youssef.kotlinflowts.events.joyfill

import com.youssef.kotlinflowts.models.joyfill.Mappable
import com.youssef.kotlinflowts.models.joyfill.utils.Document

interface ChangeEvent : Mappable {
    val changelogs: List<ChangeLog>
    val document: Document

    operator fun component1() = changelogs
    operator fun component2() = document
}