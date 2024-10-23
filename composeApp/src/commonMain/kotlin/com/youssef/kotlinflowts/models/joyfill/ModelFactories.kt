package com.youssef.kotlinflowts.models.joyfill


import com.youssef.kotlinflowts.models.joyfill.fields.ChartField
import com.youssef.kotlinflowts.models.joyfill.fields.DateField
import com.youssef.kotlinflowts.models.joyfill.fields.DropdownField
import com.youssef.kotlinflowts.models.joyfill.fields.Field
import com.youssef.kotlinflowts.models.joyfill.fields.FileField
import com.youssef.kotlinflowts.models.joyfill.fields.ImageField
import com.youssef.kotlinflowts.models.joyfill.fields.MultiSelectField
import com.youssef.kotlinflowts.models.joyfill.fields.NumberField
import com.youssef.kotlinflowts.models.joyfill.fields.SignatureField
import com.youssef.kotlinflowts.models.joyfill.fields.TableField
import com.youssef.kotlinflowts.models.joyfill.fields.TextAreaField
import com.youssef.kotlinflowts.models.joyfill.fields.TextField
import com.youssef.kotlinflowts.models.joyfill.fields.UnknownField
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Line
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Point
import com.youssef.kotlinflowts.models.joyfill.fields.chart.internal.LineImpl
import com.youssef.kotlinflowts.models.joyfill.fields.chart.internal.PointImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.BlockFieldImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.ChartFieldImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.DateFieldImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.DropdownFieldImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.FileFieldImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.ImageFieldImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.MultiSelectFieldImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.NumberFieldImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.RichTextFieldImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.SignatureFieldImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.TableFieldImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.TextAreaFieldImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.TextFieldImpl
import com.youssef.kotlinflowts.models.joyfill.fields.internal.UnknownFieldImpl
import com.youssef.kotlinflowts.models.joyfill.fields.table.Column
import com.youssef.kotlinflowts.models.joyfill.fields.table.Row
import com.youssef.kotlinflowts.models.joyfill.fields.table.internal.ImageColumnImpl
import com.youssef.kotlinflowts.models.joyfill.fields.table.internal.DropDownColumnImpl
import com.youssef.kotlinflowts.models.joyfill.fields.table.internal.TextColumnImpl
import com.youssef.kotlinflowts.models.joyfill.fields.table.internal.RowImpl
import com.youssef.kotlinflowts.models.joyfill.fields.table.internal.UnknownColumnImpl
import com.youssef.kotlinflowts.models.joyfill.internal.DocumentImpl
import com.youssef.kotlinflowts.models.joyfill.internal.FieldPositionImpl
import com.youssef.kotlinflowts.models.joyfill.internal.FileImpl
import com.youssef.kotlinflowts.models.joyfill.internal.PageImpl
import com.youssef.kotlinflowts.models.joyfill.utils.toMap
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

// Document
inline fun MutableMap<String, Any?>.toDocument(): Document = DocumentImpl(this)

inline fun String.toDocuments(): List<Document> = Json.decodeFromString<JsonArray>(this).map {
    (it as JsonObject).toDocument()
}

inline fun String.toDocument(): Document = Json.decodeFromString<JsonObject>(this).toDocument()

inline fun JsonObject.toDocument(): Document = toMap().toDocument()

inline fun Document.toMutableDocument(): MutableDocument = this as MutableDocument

// Page
inline fun MutableMap<String, Any?>.toPage(): Page = PageImpl(this)

inline fun Page.toMutablePage(): MutablePage = this as MutablePage

// File
inline fun MutableMap<String, Any?>.toFile(): File = FileImpl(this)

inline fun File.toMutableFile(): MutableFile = this as MutableFile


// Field Position
inline fun MutableMap<String, Any?>.toPosition(): FieldPosition = FieldPositionImpl(this)

inline fun MutableMap<String, Any?>.toTextField(): TextField = TextFieldImpl(this)

inline fun MutableMap<String, Any?>.toTextAreaField(): TextAreaField = TextAreaFieldImpl(this)

inline fun MutableMap<String, Any?>.toSignatureField(): SignatureField = SignatureFieldImpl(this)

inline fun MutableMap<String, Any?>.toNumberField(): NumberField = NumberFieldImpl(this)


inline fun MutableMap<String, Any?>.toDateField(): DateField = DateFieldImpl(this)

inline fun MutableMap<String, Any?>.toDropdownField(): DropdownField = DropdownFieldImpl(this)

inline fun MutableMap<String, Any?>.toMultiSelectField(): MultiSelectField = MultiSelectFieldImpl(this)

inline fun MutableMap<String, Any?>.toFileField(): FileField = FileFieldImpl(this)

inline fun MutableMap<String, Any?>.toImageField(): ImageField = ImageFieldImpl(this)

inline fun MutableMap<String, Any?>.toRow(): Row = RowImpl(this)

inline fun MutableMap<String, Any?>.toColumn(): Column = when (type()) {
    Field.Type.text -> TextColumnImpl(this)
    Field.Type.dropdown -> DropDownColumnImpl(this)
    Field.Type.image -> ImageColumnImpl(this)
    else -> UnknownColumnImpl(this)
}

inline fun MutableMap<String, Any?>.toTableField(): TableField = TableFieldImpl(this)

inline fun MutableMap<String, Any?>.toUnknownField(): UnknownField = UnknownFieldImpl(this)

inline fun MutableMap<String, Any?>.toPoint(): Point = PointImpl(this)

inline fun MutableMap<String, Any?>.toLine(): Line = LineImpl(this)

inline fun MutableMap<String, Any?>.toChartField(): ChartField = ChartFieldImpl(this)

inline fun MutableMap<String, Any?>.toField(): Field = when (type()) {
    Field.Type.text -> toTextField()
    Field.Type.textarea -> toTextAreaField()
    Field.Type.number -> toNumberField()
    Field.Type.dropdown -> toDropdownField()
    Field.Type.multiSelect -> toMultiSelectField()
    Field.Type.date -> toDateField()
    Field.Type.signature -> toSignatureField()
    Field.Type.image -> toImageField()
    Field.Type.file -> toFileField()
    Field.Type.table -> toTableField()
    Field.Type.chart -> toChartField()
    Field.Type.richText -> RichTextFieldImpl(this)
    Field.Type.block -> BlockFieldImpl(this)
    else -> toUnknownField()
}

inline fun MutableMap<String, Any?>.type(): Field.Type = try {
    Field.Type.valueOf(this[Field::type.name] as String)
} catch (e: IllegalArgumentException) {
    Field.Type.unknown
}