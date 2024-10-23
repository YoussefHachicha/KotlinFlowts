package com.youssef.kotlinflowts

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform