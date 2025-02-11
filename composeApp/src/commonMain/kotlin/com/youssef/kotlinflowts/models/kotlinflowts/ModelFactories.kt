package com.youssef.kotlinflowts.models.kotlinflowts


import com.youssef.kotlinflowts.models.kotlinflowts.components.BlockComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.ChartComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.ColumnComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.DateFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.DropdownComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.components.FileComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.ImageComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.MultiSelectComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.NumberFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.RichTextComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.RowComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.SignatureComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextFieldAreaComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.TextFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.UnknownComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Line
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Point
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.internal.LineImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.internal.PointImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.BlockComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.ChartComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.ColumnComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.DateFieldComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.DropdownComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.FileComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.ImageComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.MultiSelectComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.NumberFieldComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.RichTextComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.RowComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.SignatureComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.TableComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.TextComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.TextFieldAreaComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.TextFieldComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.internal.UnknownComponentImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Column
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Row
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.internal.ImageColumnImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.internal.DropDownColumnImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.internal.TextColumnImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.internal.RowImpl
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.internal.UnknownColumnImpl
import com.youssef.kotlinflowts.models.kotlinflowts.internal.AppImpl
import com.youssef.kotlinflowts.models.kotlinflowts.internal.ComponentPositionImpl
import com.youssef.kotlinflowts.models.kotlinflowts.internal.FileImpl
import com.youssef.kotlinflowts.models.kotlinflowts.internal.ScreenImpl
import com.youssef.kotlinflowts.models.kotlinflowts.utils.App
import com.youssef.kotlinflowts.models.kotlinflowts.utils.toMap
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

inline fun MutableMap<String, Any?>.toTextFieldComponent(): TextFieldComponent = TextFieldComponentImpl(this)

inline fun MutableMap<String, Any?>.toTextFieldAreaComponent(): TextFieldAreaComponent = TextFieldAreaComponentImpl(this)

inline fun MutableMap<String, Any?>.toSignatureComponent(): SignatureComponent = SignatureComponentImpl(this)

inline fun MutableMap<String, Any?>.toNumberFieldComponent(): NumberFieldComponent = NumberFieldComponentImpl(this)


inline fun MutableMap<String, Any?>.toDateFieldComponent(): DateFieldComponent = DateFieldComponentImpl(this)

inline fun MutableMap<String, Any?>.toDropdownComponent(): DropdownComponent = DropdownComponentImpl(this)

inline fun MutableMap<String, Any?>.toMultiSelectComponent(): MultiSelectComponent = MultiSelectComponentImpl(this)

inline fun MutableMap<String, Any?>.toFileComponent(): FileComponent = FileComponentImpl(this)

inline fun MutableMap<String, Any?>.toImageComponent(): ImageComponent = ImageComponentImpl(this)

inline fun MutableMap<String, Any?>.toRow(): Row = RowImpl(this)

inline fun MutableMap<String, Any?>.toColumn(): Column = when (type()) {
    Component.Type.textField -> TextColumnImpl(this)
    Component.Type.dropdown  -> DropDownColumnImpl(this)
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

inline fun MutableMap<String, Any?>.toColumnComponent(): ColumnComponent = ColumnComponentImpl(this)

inline fun MutableMap<String, Any?>.toRowComponent(): RowComponent = RowComponentImpl(this)

inline fun MutableMap<String, Any?>.toComponent(): Component = when (type()) {
        Component.Type.text         -> toTextComponent()
        Component.Type.textField     -> toTextFieldComponent()
        Component.Type.textFieldArea -> toTextFieldAreaComponent()
        Component.Type.numberField   -> toNumberFieldComponent()
        Component.Type.dropdown      -> toDropdownComponent()
        Component.Type.multiSelect -> toMultiSelectComponent()
        Component.Type.dateField   -> toDateFieldComponent()
        Component.Type.signature   -> toSignatureComponent()
        Component.Type.image -> toImageComponent()
        Component.Type.file -> toFileComponent()
        Component.Type.table -> toTableComponent()
        Component.Type.chart -> toChartComponent()
        Component.Type.richText -> toRichTextComponent()
        Component.Type.block -> toBlockComponent()
        Component.Type.column -> toColumnComponent()
        Component.Type.row -> toRowComponent()
        else -> toUnknownComponent()
}

inline fun MutableMap<String, Any?>.type(): Component.Type = try {
    Component.Type.valueOf(this[Component::type.name] as String)
} catch (e: IllegalArgumentException) {
    Component.Type.unknown
}