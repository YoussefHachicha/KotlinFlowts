package com.youssef.kotlinflowts.models.joyfill


import com.youssef.kotlinflowts.models.joyfill.components.BlockComponent
import com.youssef.kotlinflowts.models.joyfill.components.ChartComponent
import com.youssef.kotlinflowts.models.joyfill.components.ColumnComponent
import com.youssef.kotlinflowts.models.joyfill.components.DateComponent
import com.youssef.kotlinflowts.models.joyfill.components.DropdownComponent
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.components.FileComponent
import com.youssef.kotlinflowts.models.joyfill.components.ImageComponent
import com.youssef.kotlinflowts.models.joyfill.components.MultiSelectComponent
import com.youssef.kotlinflowts.models.joyfill.components.NumberComponent
import com.youssef.kotlinflowts.models.joyfill.components.RichTextComponent
import com.youssef.kotlinflowts.models.joyfill.components.SignatureComponent
import com.youssef.kotlinflowts.models.joyfill.components.TableComponent
import com.youssef.kotlinflowts.models.joyfill.components.TextAreaComponent
import com.youssef.kotlinflowts.models.joyfill.components.TextComponent
import com.youssef.kotlinflowts.models.joyfill.components.UnknownComponent
import com.youssef.kotlinflowts.models.joyfill.components.chart.Line
import com.youssef.kotlinflowts.models.joyfill.components.chart.Point
import com.youssef.kotlinflowts.models.joyfill.components.chart.internal.LineImpl
import com.youssef.kotlinflowts.models.joyfill.components.chart.internal.PointImpl
import com.youssef.kotlinflowts.models.joyfill.components.internal.BlockComponentImpl
import com.youssef.kotlinflowts.models.joyfill.components.internal.ChartComponentImpl
import com.youssef.kotlinflowts.models.joyfill.components.internal.ColumnComponentImpl
import com.youssef.kotlinflowts.models.joyfill.components.internal.DateComponentImpl
import com.youssef.kotlinflowts.models.joyfill.components.internal.DropdownComponentImpl
import com.youssef.kotlinflowts.models.joyfill.components.internal.FileComponentImpl
import com.youssef.kotlinflowts.models.joyfill.components.internal.ImageComponentImpl
import com.youssef.kotlinflowts.models.joyfill.components.internal.MultiSelectComponentImpl
import com.youssef.kotlinflowts.models.joyfill.components.internal.NumberComponentImpl
import com.youssef.kotlinflowts.models.joyfill.components.internal.RichTextComponentImpl
import com.youssef.kotlinflowts.models.joyfill.components.internal.SignatureComponentImpl
import com.youssef.kotlinflowts.models.joyfill.components.internal.TableComponentImpl
import com.youssef.kotlinflowts.models.joyfill.components.internal.TextAreaComponentImpl
import com.youssef.kotlinflowts.models.joyfill.components.internal.TextComponentImpl
import com.youssef.kotlinflowts.models.joyfill.components.internal.UnknownComponentImpl
import com.youssef.kotlinflowts.models.joyfill.components.table.Column
import com.youssef.kotlinflowts.models.joyfill.components.table.Row
import com.youssef.kotlinflowts.models.joyfill.components.table.internal.ImageColumnImpl
import com.youssef.kotlinflowts.models.joyfill.components.table.internal.DropDownColumnImpl
import com.youssef.kotlinflowts.models.joyfill.components.table.internal.TextColumnImpl
import com.youssef.kotlinflowts.models.joyfill.components.table.internal.RowImpl
import com.youssef.kotlinflowts.models.joyfill.components.table.internal.UnknownColumnImpl
import com.youssef.kotlinflowts.models.joyfill.internal.AppImpl
import com.youssef.kotlinflowts.models.joyfill.internal.ComponentPositionImpl
import com.youssef.kotlinflowts.models.joyfill.internal.FileImpl
import com.youssef.kotlinflowts.models.joyfill.internal.ScreenImpl
import com.youssef.kotlinflowts.models.joyfill.utils.App
import com.youssef.kotlinflowts.models.joyfill.utils.ID
import com.youssef.kotlinflowts.models.joyfill.utils.toMap
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

// App
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

inline fun MutableMap<String, Any?>.toBlockComponent(): BlockComponent = BlockComponentImpl(this)

inline fun MutableMap<String, Any?>.toRichTextComponent(): RichTextComponent = RichTextComponentImpl(this)

// Extension function to create component
inline fun MutableMap<String, Any?>.toColumnComponent(): ColumnComponent {
    // Validate required fields before creating component
    requireNotNull(this[ID]) { "ID cannot be null for column component" }
    requireNotNull(this[Component::title.name]) { "Title cannot be null for column component" }
    requireNotNull(this[Component::identifier.name]) { "Identifier cannot be null for column component" }
    requireNotNull(this[Component::type.name]) { "Type cannot be null for column component" }
    return ColumnComponentImpl(this)
}

inline fun MutableMap<String, Any?>.toComponent(): Component{
    val type = this[Component::type.name] as? String
    requireNotNull(type) { "Component type cannot be null" }

    return when (type()) {
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
        Component.Type.richText -> toRichTextComponent()
        Component.Type.block -> toBlockComponent()
        Component.Type.column -> (this as? MutableMap<String, Any?>)?.toColumnComponent()
            ?: throw IllegalStateException("Invalid column component data")
        else -> toUnknownComponent()
    }
}

inline fun MutableMap<String, Any?>.type(): Component.Type = try {
    Component.Type.valueOf(this[Component::type.name] as String)
} catch (e: IllegalArgumentException) {
    Component.Type.unknown
}