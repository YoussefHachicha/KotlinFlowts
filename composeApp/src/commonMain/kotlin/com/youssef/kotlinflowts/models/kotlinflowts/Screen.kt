package com.youssef.kotlinflowts.models.kotlinflowts

interface Screen : Mappable {
    val id: String
    val name: String
    val positions: List<ComponentPosition>
}