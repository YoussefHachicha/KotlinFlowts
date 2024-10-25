package com.youssef.kotlinflowts.models.joyfill

interface Screen : Mappable {
    val id: String
    val name: String
    val positions: List<ComponentPosition>
}