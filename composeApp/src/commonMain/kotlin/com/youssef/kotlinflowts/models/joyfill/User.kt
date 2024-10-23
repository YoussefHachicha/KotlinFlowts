package com.youssef.kotlinflowts.models.joyfill

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("_id") val id: String,
    val organization: String,
    val type: UserType,
    val identifier: String,
    val createdOn: Long,
    val firstName: String,
    val lastName: String,
    val email: String
)
