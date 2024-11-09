package com.youssef.kotlinflowts.events.kotlinflowts

import com.youssef.kotlinflowts.models.kotlinflowts.Mappable
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App

interface ChangeEvent : Mappable {
    val changelogs: List<ChangeLog>
    val app: App

    operator fun component1() = changelogs
    operator fun component2() = app
}