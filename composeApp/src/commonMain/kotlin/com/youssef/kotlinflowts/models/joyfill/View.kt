package com.youssef.kotlinflowts.models.joyfill

interface View : Mappable {
    val id: String
    val type: String
    val pages: List<Page>
    val pageOrder: List<String>
}