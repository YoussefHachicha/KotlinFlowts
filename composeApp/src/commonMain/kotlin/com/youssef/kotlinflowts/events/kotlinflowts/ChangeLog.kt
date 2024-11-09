package com.youssef.kotlinflowts.events.kotlinflowts

import com.youssef.kotlinflowts.models.kotlinflowts.Mappable

interface ChangeLog : Mappable {
    val id: String
    val identifier: String
    val fieldId: String
    val fieldIdentifier: String
    val pageId: String
    val fileId: String
    val fieldPositionId: String
    val target: String
    val createdOn: Long
    val change: Change
    val sdk: String
    val v: Int
}