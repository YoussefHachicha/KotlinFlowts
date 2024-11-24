package colorPicker.math.model

import colorPicker.math.Color
import colorPicker.math.ColorComponentInfo
import colorPicker.math.ColorSpace
import colorPicker.math.HueColor
import colorPicker.math.internal.doCreate
import colorPicker.math.internal.fromPolarModel
import colorPicker.math.internal.polarComponentInfo

/**
 * Oklch color model, the cylindrical representation of [Oklab].
 *
 * | Component  | Description                               | Range      |
 * | ---------- | ----------------------------------------- | ---------- |
 * | [l]        | lightness                                 | `[0, 1]`   |
 * | [c]        | chroma                                    | `[0, 1]`   |
 * | [h]        | hue, degrees, `NaN` for monochrome colors | `[0, 360)` |
 */
data class Oklch(val l: Float, val c: Float, override val h: Float, override val alpha: Float = 1f) : Color,
    HueColor {
    /** Default constructors for the [Oklch] color model. */
    companion object : ColorSpace<Oklch> {
        override val name: String get() = "Oklch"
        override val components: List<ColorComponentInfo> = polarComponentInfo("LCH")
        override fun convert(color: Color): Oklch = color.toOklch()
        override fun create(components: FloatArray): Oklch = doCreate(components, ::Oklch)
    }

    constructor(l: Number, c: Number, h: Number, alpha: Number = 1f)
            : this(l.toFloat(), c.toFloat(), h.toFloat(), alpha.toFloat())

    override val space: ColorSpace<Oklch> get() = Oklch

    override fun toSRGB(): RGB = toOklab().toSRGB()
    override fun toXYZ(): XYZ = toOklab().toXYZ()
    override fun toOklab(): Oklab = fromPolarModel(c, h) { a, b -> Oklab(l, a, b, alpha) }
    override fun toOklch(): Oklch = this
    override fun toArray(): FloatArray = floatArrayOf(l, c, h, alpha)
}
