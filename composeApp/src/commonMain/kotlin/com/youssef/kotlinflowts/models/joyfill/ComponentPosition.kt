package com.youssef.kotlinflowts.models.joyfill

interface ComponentPosition : Mappable {
    val id: String
    val componentId: String
    val displayType: String?
    val y: Double
    val x: Double
    val format: String?
    val fontColor: String?
    val fontWeight: String?
    val fontSize: Double?
    val textTransform: String?
    val textOverflow: String?
    val textAlign: String?
}