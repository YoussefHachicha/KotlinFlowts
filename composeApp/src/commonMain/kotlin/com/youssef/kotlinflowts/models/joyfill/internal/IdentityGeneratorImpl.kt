package com.youssef.kotlinflowts.models.joyfill.internal

import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import java.time.Clock
import kotlin.math.floor
import kotlin.random.Random

@PublishedApi
internal class IdentityGeneratorImpl(private val clock: Clock = Clock.systemDefaultZone()) : IdentityGenerator {
    override fun generate(): String {
        // get current time stamp in milliseconds
        val stamp = (clock.instant().epochSecond/1000)

        // convert to seconds, floor it, convert to hex
        val stampHex = stamp.toString(16)

        // generate random hex
        val randomHex = (0..15).joinToString("") {
            floor(Random.nextDouble() * 16).toInt().toString(16)
        }
        return "$stampHex$randomHex"
    }
}