package com.youssef.kotlinflowts.builder.joyfill

import com.youssef.kotlinflowts.models.joyfill.FieldPosition
import com.youssef.kotlinflowts.models.joyfill.File
import com.youssef.kotlinflowts.models.joyfill.MutableFile
import com.youssef.kotlinflowts.models.joyfill.MutablePage
import com.youssef.kotlinflowts.models.joyfill.Page
import com.youssef.kotlinflowts.models.joyfill.fields.DateField
import com.youssef.kotlinflowts.models.joyfill.fields.DropdownField
import com.youssef.kotlinflowts.models.joyfill.fields.Field
import com.youssef.kotlinflowts.models.joyfill.fields.TableField
import com.youssef.kotlinflowts.models.joyfill.fields.ValueBasedField
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Axis
import com.youssef.kotlinflowts.models.joyfill.fields.chart.Line
import com.youssef.kotlinflowts.models.joyfill.fields.table.Column
import com.youssef.kotlinflowts.models.joyfill.fields.table.Row
import com.youssef.kotlinflowts.models.joyfill.toChartField
import com.youssef.kotlinflowts.models.joyfill.toDateField
import com.youssef.kotlinflowts.models.joyfill.toDropdownField
import com.youssef.kotlinflowts.models.joyfill.toFile
import com.youssef.kotlinflowts.models.joyfill.toFileField
import com.youssef.kotlinflowts.models.joyfill.toImageField
import com.youssef.kotlinflowts.models.joyfill.toMultiSelectField
import com.youssef.kotlinflowts.models.joyfill.toMutableFile
import com.youssef.kotlinflowts.models.joyfill.toMutablePage
import com.youssef.kotlinflowts.models.joyfill.toNumberField
import com.youssef.kotlinflowts.models.joyfill.toPage
import com.youssef.kotlinflowts.models.joyfill.toPosition
import com.youssef.kotlinflowts.models.joyfill.toSignatureField
import com.youssef.kotlinflowts.models.joyfill.toTableField
import com.youssef.kotlinflowts.models.joyfill.toTextAreaField
import com.youssef.kotlinflowts.models.joyfill.toTextField
import com.youssef.kotlinflowts.models.joyfill.utils.AXIS_X_MAX
import com.youssef.kotlinflowts.models.joyfill.utils.AXIS_X_MIN
import com.youssef.kotlinflowts.models.joyfill.utils.AXIS_X_TITLE
import com.youssef.kotlinflowts.models.joyfill.utils.AXIS_Y_MAX
import com.youssef.kotlinflowts.models.joyfill.utils.AXIS_Y_MIN
import com.youssef.kotlinflowts.models.joyfill.utils.AXIS_Y_TITLE
import com.youssef.kotlinflowts.models.joyfill.utils.Attachment
import com.youssef.kotlinflowts.models.joyfill.utils.COLUMNS
import com.youssef.kotlinflowts.models.joyfill.utils.COLUMN_ORDER
import com.youssef.kotlinflowts.models.joyfill.utils.ID
import com.youssef.kotlinflowts.models.joyfill.utils.Option2
import com.youssef.kotlinflowts.models.joyfill.utils.POSITIONS

internal fun Page(
    id: String,
    name: String,
    positions: MutableList<FieldPosition>,
): MutablePage = mutableMapOf<String, Any?>(
    ID to id,
    Page::name.name to name,
    POSITIONS to positions
).toPage().toMutablePage()

internal fun File(
    id: String,
    name: String,
    pages: MutableList<Page>,
    pageOrder: MutableList<String>,
): MutableFile = mutableMapOf<String, Any?>(
    ID to id,
    File::name.name to name,
    File::pages.name to pages,
    File::pageOrder.name to pageOrder
).toFile().toMutableFile()

internal fun FieldPosition(
    id: String,
    field: String,
    displayType: String?,
    format: String?
): FieldPosition = mutableMapOf<String, Any?>(
    ID to id,
    FieldPosition::field.name to field,
    FieldPosition::displayType.name to displayType,
    FieldPosition::format.name to format
).toPosition()

private fun <V> ValueBasedField(
    id: String,
    title: String,
    identifier: String,
    type: Field.Type,
    readonly: Boolean,
    value: V?
) = mutableMapOf(
    ID to id,
    Field::title.name to title,
    Field::identifier.name to identifier,
    ValueBasedField<*>::value.name to value,
    Field::disabled.name to readonly,
    Field::type.name to type.name
)

internal fun TextField(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    value: String?
) = ValueBasedField(id, title, identifier, Field.Type.text, readonly, value).toTextField()

internal fun TextArea(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    value: String?
) = ValueBasedField(id, title, identifier, Field.Type.textarea, readonly, value).toTextAreaField()

internal fun SignatureField(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    value: String?
) = ValueBasedField(id, title, identifier, Field.Type.signature, readonly, value).toSignatureField()

internal fun NumberField(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    value: Number?
) = ValueBasedField(id, title, identifier, Field.Type.number, readonly, value?.toDouble()).toNumberField()

internal fun DateField(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    format: String?,
    value: Long?
) = mutableMapOf<String, Any?>(
    ID to id,
    Field::title.name to title,
    Field::identifier.name to identifier,
    ValueBasedField<*>::value.name to value,
    Field::disabled.name to readonly,
    DateField::format.name to format,
    Field::type.name to Field.Type.date.name
).toDateField()

internal fun DropdownField(
    id: String,
    title: String,
    identifier: String,
    options: List<Option2>,
    readonly: Boolean,
    selected: Option2?
) = mutableMapOf(
    ID to id,
    Field::title.name to title,
    Field::identifier.name to identifier,
    ValueBasedField<*>::value.name to selected?.value,
    DropdownField::options.name to options.map { it.toMap() }.toMutableList(),
    Field::disabled.name to readonly,
    Field::type.name to Field.Type.dropdown.name
).toDropdownField()

internal fun MultiSelectField(
    id: String,
    title: String,
    identifier: String,
    options: List<Option2>,
    selected: List<String>
) = mutableMapOf<String, Any?>(
    ID to id,
    Field::title.name to title,
    Field::identifier.name to identifier,
    ValueBasedField<*>::value.name to selected,
    DropdownField::options.name to options.map { it.toMap() }.toMutableList(),
    Field::type.name to Field.Type.multiSelect.name
).toMultiSelectField()

internal fun FileField(
    id: String,
    title: String,
    identifier: String,
    value: List<Attachment>
) = mutableMapOf<String, Any?>(
    ID to id,
    Field::title.name to title,
    Field::identifier.name to identifier,
    ValueBasedField<*>::value.name to value,
    Field::type.name to Field.Type.file.name
).toFileField()

internal fun ImageField(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    value: List<Attachment>
) = mutableMapOf<String, Any?>(
    ID to id,
    Field::title.name to title,
    Field::identifier.name to identifier,
    ValueBasedField<*>::value.name to value.map { it.toMap() }.toMutableList(),
    Field::disabled.name to readonly,
    Field::type.name to Field.Type.image.name
).toImageField()

internal fun TableField(
    id: String,
    title: String,
    identifier: String,
    columns: List<Column>
) = mutableMapOf<String, Any?>(
    ID to id,
    Field::title.name to title,
    Field::identifier.name to identifier,
    ValueBasedField<*>::value.name to mutableListOf<Row>(),
    Field::type.name to Field.Type.table.name,
    COLUMNS to columns.map { it.toMap() }.toMutableList(),
    COLUMN_ORDER to columns.map { it.id }.toMutableList(),
    TableField::rowOrder.name to mutableListOf<String>()
).toTableField()

internal fun ChartField(
    id: String,
    title: String,
    identifier: String,
    y: Axis,
    x: Axis,
    lines: List<Line>
) = mutableMapOf<String, Any?>(
    ID to id,
    Field::title.name to title,
    Field::identifier.name to identifier,
    AXIS_Y_TITLE to y.label,
    AXIS_Y_MIN to y.min,
    AXIS_Y_MAX to y.max,
    AXIS_X_TITLE to x.label,
    AXIS_X_MIN to x.min,
    AXIS_X_MAX to x.max,
    ValueBasedField<*>::value.name to lines.map { it.toMap() }.toMutableList(),
    Field::type.name to Field.Type.chart.name,
).toChartField()