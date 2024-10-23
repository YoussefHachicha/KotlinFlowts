package com.youssef.kotlinflowts.models.joyfill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Group(
    @SerialName("_id")
    val id: String,
    val identifier: String,
    val title: String
)