package com.youssef.kotlinflowts.models.kotlinflowts

interface View : Mappable {
    val id: String
    val type: String
    val screens: List<Screen>
    val pageOrder: List<String>
}