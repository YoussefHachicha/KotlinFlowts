package com.youssef.kotlinflowts.models.joyfill

interface Page : Mappable {
    val id: String
    val name: String
    val positions: List<FieldPosition>
}