package com.youssef.kotlinflowts

class JsPlatform: Platform {
    override val name: String = "Js web"
}

actual fun getPlatform(): Platform = JsPlatform()