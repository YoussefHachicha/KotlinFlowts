package com.youssef.kotlinflowts.models.joyfill

interface File : Mappable {
    val id: String
    val identifier: String
    val name: String
    val pages: List<Page>
    val pageOrder: List<String>
    val views: List<View>
}