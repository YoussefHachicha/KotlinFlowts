package com.youssef.kotlinflowts.models.joyfill.fields.internal

import com.youssef.kotlinflowts.models.joyfill.fields.ChartField
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Axis
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Line
import com.youssef.kotlinflowts.models.joyfill.toLine
import com.youssef.kotlinflowts.models.joyfill.utils.AXIS_X_MAX
import com.youssef.kotlinflowts.models.joyfill.utils.AXIS_X_MIN
import com.youssef.kotlinflowts.models.joyfill.utils.AXIS_X_TITLE
import com.youssef.kotlinflowts.models.joyfill.utils.AXIS_Y_MAX
import com.youssef.kotlinflowts.models.joyfill.utils.AXIS_Y_MIN
import com.youssef.kotlinflowts.models.joyfill.utils.AXIS_Y_TITLE

@PublishedApi
internal open class ChartFieldImpl(
    wrapped: MutableMap<String, Any?>
) : AbstractListBasedField<Line>(wrapped), ChartField {

    override fun factory(map: MutableMap<String, Any?>): Line = map.toLine()

    override val y: Axis
        get() = Axis(
            label = wrapped[AXIS_Y_TITLE] as String,
            min = wrapped[AXIS_Y_MIN] as? Double ?: 0.0,
            max = wrapped[AXIS_Y_MAX] as? Double ?: 100.0,
        )

    override val x: Axis
        get() = Axis(
            label = wrapped[AXIS_X_TITLE] as String,
            min = wrapped[AXIS_X_MIN] as? Double ?: 0.0,
            max = wrapped[AXIS_X_MAX] as? Double ?: 100.0,
        )
}