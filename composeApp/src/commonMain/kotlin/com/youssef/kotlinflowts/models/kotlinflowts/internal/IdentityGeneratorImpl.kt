package com.youssef.kotlinflowts.models.kotlinflowts.internal

import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import kotlin.math.floor
import kotlin.random.Random

@PublishedApi
internal class IdentityGeneratorImpl(private val clock: kotlinx.datetime.Clock = kotlinx.datetime.Clock.System) : IdentityGenerator {
    override fun generate(): String {
        // get current time stamp in milliseconds
        val stamp = (clock.now().toEpochMilliseconds() /1000)

        // convert to seconds, floor it, convert to hex
        val stampHex = stamp.toString(16)

        // generate random hex
        val randomHex = (0..15).joinToString("") {
            floor(Random.nextDouble() * 16).toInt().toString(16)
        }
        return "$stampHex$randomHex"
    }
}