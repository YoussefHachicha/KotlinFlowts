package com.youssef.kotlinflowts.models.kotlinflowts

interface ComponentPosition : Mappable {
    val id: String
    val componentId: String
    val displayType: String?
    val depth: Int
    val builderId: String
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