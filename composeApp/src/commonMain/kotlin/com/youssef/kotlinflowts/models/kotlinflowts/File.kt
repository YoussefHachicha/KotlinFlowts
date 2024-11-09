package com.youssef.kotlinflowts.models.kotlinflowts

interface File : Mappable {
    val id: String
    val identifier: String
    val name: String
    val screens: List<Screen>
    val screenOrder: List<String>
    val views: List<View>
}