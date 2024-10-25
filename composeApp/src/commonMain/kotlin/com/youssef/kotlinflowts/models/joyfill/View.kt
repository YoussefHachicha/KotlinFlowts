package com.youssef.kotlinflowts.models.joyfill

interface View : Mappable {
    val id: String
    val type: String
    val screens: List<Screen>
    val pageOrder: List<String>
}