package com.youssef.kotlinflowts.models.joyfill


import com.youssef.kotlinflowts.models.joyfill.fields.ChartComponent
import com.youssef.kotlinflowts.models.joyfill.fields.DateComponent
import com.youssef.kotlinflowts.models.joyfill.fields.DropdownComponent
import com.youssef.kotlinflowts.models.joyfill.fields.Component
import com.youssef.kotlinflowts.models.joyfill.fields.FileComponent
import com.youssef.kotlinflowts.models.joyfill.fields.ImageComponent
import com.youssef.kotlinflowts.models.joyfill.fields.MultiSelectComponent
import com.youssef.kotlinflowts.models.joyfill.fields.NumberComponent
import com.youssef.kotlinflowts.models.joyfill.fields.SignatureComponent
import com.youssef.kotlinflowts.models.joyfill.fields.TableComponent
import com.youssef.kotlinflowts.models.joyfill.fields.TextAreaComponent
import com.youssef.kotlinflowts.models.joyfill.fields.TextComponent
import com.youssef.kotlinflowts.models.joyfill.fields.UnknownComponent
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Line
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Point
import com.youssef.kotlinflowts.models.joyfill.fields.chart.internal.LineImpl
import com.youssef.kotlinflowts.models.joyfill.fields.chart.internal.PointImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.BlockComponentImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.ChartComponentImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.DateComponentImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.DropdownComponentImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.FileComponentImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.ImageComponentImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.MultiSelectComponentImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.NumberComponentImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.RichTextComponentImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.SignatureComponentImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.TableComponentImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.TextAreaComponentImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.TextComponentImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.UnknownComponentImpl
import com.youssef.kotlinflowts.models.joyfill.fields.table.Column
import com.youssef.kotlinflowts.models.joyfill.fields.table.Row
import com.youssef.kotlinflowts.models.joyfill.fields.table.internal.ImageColumnImpl
import com.youssef.kotlinflowts.models.joyfill.fields.table.internal.DropDownColumnImpl
import com.youssef.kotlinflowts.models.joyfill.fields.table.internal.TextColumnImpl
import com.youssef.kotlinflowts.models.joyfill.fields.table.internal.RowImpl
import com.youssef.kotlinflowts.models.joyfill.fields.table.internal.UnknownColumnImpl
import com.youssef.kotlinflowts.models.joyfill.internal.AppImpl
import com.youssef.kotlinflowts.models.joyfill.internal.ComponentPositionImpl
import com.youssef.kotlinflowts.models.joyfill.internal.FileImpl
import com.youssef.kotlinflowts.models.joyfill.internal.ScreenImpl
import com.youssef.kotlinflowts.models.joyfill.utils.App
import com.youssef.kotlinflowts.models.joyfill.utils.toMap
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

// Document
inline fun MutableMap<String, Any?>.toApp(): App = AppImpl(this)

inline fun String.toApps(): List<App> = Json.decodeFromString<JsonArray>(this).map {
    (it as JsonObject).toApp()
}

inline fun String.toApp(): App = Json.decodeFromString<JsonObject>(this).toApp()

inline fun JsonObject.toApp(): App = toMap().toApp()

inline fun App.toMutableApp(): MutableApp = this as MutableApp

// Screen
inline fun MutableMap<String, Any?>.toScreen(): Screen = ScreenImpl(this)

inline fun Screen.toMutableScreen(): MutableScreen = this as MutableScreen

// File
inline fun MutableMap<String, Any?>.toFile(): File = FileImpl(this)

inline fun File.toMutableFile(): MutableFile = this as MutableFile


// Component Position
inline fun MutableMap<String, Any?>.toPosition(): ComponentPosition = ComponentPositionImpl(this)

// Component
inline fun MutableMap<String, Any?>.toTextComponent(): TextComponent = TextComponentImpl(this)

inline fun MutableMap<String, Any?>.toTextAreaComponent(): TextAreaComponent = TextAreaComponentImpl(this)

inline fun MutableMap<String, Any?>.toSignatureComponent(): SignatureComponent = SignatureComponentImpl(this)

inline fun MutableMap<String, Any?>.toNumberComponent(): NumberComponent = NumberComponentImpl(this)


inline fun MutableMap<String, Any?>.toDateComponent(): DateComponent = DateComponentImpl(this)

inline fun MutableMap<String, Any?>.toDropdownComponent(): DropdownComponent = DropdownComponentImpl(this)

inline fun MutableMap<String, Any?>.toMultiSelectComponent(): MultiSelectComponent = MultiSelectComponentImpl(this)

inline fun MutableMap<String, Any?>.toFileComponent(): FileComponent = FileComponentImpl(this)

inline fun MutableMap<String, Any?>.toImageComponent(): ImageComponent = ImageComponentImpl(this)

inline fun MutableMap<String, Any?>.toRow(): Row = RowImpl(this)

inline fun MutableMap<String, Any?>.toColumn(): Column = when (type()) {
    Component.Type.text -> TextColumnImpl(this)
    Component.Type.dropdown -> DropDownColumnImpl(this)
    Component.Type.image -> ImageColumnImpl(this)
    else -> UnknownColumnImpl(this)
}

inline fun MutableMap<String, Any?>.toTableComponent(): TableComponent = TableComponentImpl(this)

inline fun MutableMap<String, Any?>.toUnknownComponent(): UnknownComponent = UnknownComponentImpl(this)

inline fun MutableMap<String, Any?>.toPoint(): Point = PointImpl(this)

inline fun MutableMap<String, Any?>.toLine(): Line = LineImpl(this)

inline fun MutableMap<String, Any?>.toChartComponent(): ChartComponent = ChartComponentImpl(this)

inline fun MutableMap<String, Any?>.toComponent(): Component = when (type()) {
    Component.Type.text -> toTextComponent()
    Component.Type.textarea -> toTextAreaComponent()
    Component.Type.number -> toNumberComponent()
    Component.Type.dropdown -> toDropdownComponent()
    Component.Type.multiSelect -> toMultiSelectComponent()
    Component.Type.date -> toDateComponent()
    Component.Type.signature -> toSignatureComponent()
    Component.Type.image -> toImageComponent()
    Component.Type.file -> toFileComponent()
    Component.Type.table -> toTableComponent()
    Component.Type.chart -> toChartComponent()
    Component.Type.richText -> RichTextComponentImpl(this)
    Component.Type.block -> BlockComponentImpl(this)
    else -> toUnknownComponent()
}

inline fun MutableMap<String, Any?>.type(): Component.Type = try {
    Component.Type.valueOf(this[Component::type.name] as String)
} catch (e: IllegalArgumentException) {
    Component.Type.unknown
}