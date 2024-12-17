package com.youssef.kotlinflowts.builder.kotlinflowts

import com.youssef.kotlinflowts.models.kotlinflowts.ComponentPosition
import com.youssef.kotlinflowts.models.kotlinflowts.File
import com.youssef.kotlinflowts.models.kotlinflowts.MutableFile
import com.youssef.kotlinflowts.models.kotlinflowts.MutableScreen
import com.youssef.kotlinflowts.models.kotlinflowts.Screen
import com.youssef.kotlinflowts.models.kotlinflowts.components.DateFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.DropdownComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.MultiSelectComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.components.TableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.ValueBasedComponent
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Axis
import com.youssef.kotlinflowts.models.kotlinflowts.components.chart.Line
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Column
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Row
import com.youssef.kotlinflowts.models.kotlinflowts.toChartComponent
import com.youssef.kotlinflowts.models.kotlinflowts.toColumnComponent
import com.youssef.kotlinflowts.models.kotlinflowts.toDateFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.toDropdownComponent
import com.youssef.kotlinflowts.models.kotlinflowts.toFile
import com.youssef.kotlinflowts.models.kotlinflowts.toFileComponent
import com.youssef.kotlinflowts.models.kotlinflowts.toImageComponent
import com.youssef.kotlinflowts.models.kotlinflowts.toMultiSelectComponent
import com.youssef.kotlinflowts.models.kotlinflowts.toMutableFile
import com.youssef.kotlinflowts.models.kotlinflowts.toMutableScreen
import com.youssef.kotlinflowts.models.kotlinflowts.toNumberFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.toScreen
import com.youssef.kotlinflowts.models.kotlinflowts.toPosition
import com.youssef.kotlinflowts.models.kotlinflowts.toRowComponent
import com.youssef.kotlinflowts.models.kotlinflowts.toSignatureComponent
import com.youssef.kotlinflowts.models.kotlinflowts.toTableComponent
import com.youssef.kotlinflowts.models.kotlinflowts.toTextComponent
import com.youssef.kotlinflowts.models.kotlinflowts.toTextFieldAreaComponent
import com.youssef.kotlinflowts.models.kotlinflowts.toTextFieldComponent
import com.youssef.kotlinflowts.models.kotlinflowts.utils.AXIS_X_MAX
import com.youssef.kotlinflowts.models.kotlinflowts.utils.AXIS_X_MIN
import com.youssef.kotlinflowts.models.kotlinflowts.utils.AXIS_X_TITLE
import com.youssef.kotlinflowts.models.kotlinflowts.utils.AXIS_Y_MAX
import com.youssef.kotlinflowts.models.kotlinflowts.utils.AXIS_Y_MIN
import com.youssef.kotlinflowts.models.kotlinflowts.utils.AXIS_Y_TITLE
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Attachment
import com.youssef.kotlinflowts.models.kotlinflowts.utils.COLUMNS
import com.youssef.kotlinflowts.models.kotlinflowts.utils.COLUMN_ORDER
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2
import com.youssef.kotlinflowts.models.kotlinflowts.utils.POSITIONS

internal fun screen(
    id: String,
    name: String,
    positions: MutableList<ComponentPosition>,
): MutableScreen = mutableMapOf<String, Any?>(
    ID to id,
    Screen::name.name to name,
    POSITIONS to positions
).toScreen().toMutableScreen()

internal fun file(
    id: String,
    name: String,
    screens: MutableList<Screen>,
    screenOrder: MutableList<String>,
): MutableFile = mutableMapOf<String, Any?>(
    ID to id,
    File::name.name to name,
    File::screens.name to screens,
    File::screenOrder.name to screenOrder
).toFile().toMutableFile()

internal fun componentPosition(
    id: String,
    component: String,
    displayType: String?,
    depth: Int,
    builderId: String,
    format: String?
): ComponentPosition = mutableMapOf<String, Any?>(
    ID to id,
    ComponentPosition::componentId.name to component,
    ComponentPosition::displayType.name to displayType,
    ComponentPosition::depth.name to depth,
    ComponentPosition::builderId.name to builderId,
    ComponentPosition::format.name to format
).toPosition()

private fun <V> valueBasedComponent(
    id: String,
    title: String,
    identifier: String,
    type: Component.Type,
    readonly: Boolean,
    depth: Int,
    builderId: String,
    value: V?
) = mutableMapOf(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    ValueBasedComponent<*>::value.name to value,
    Component::disabled.name to readonly,
    Component::depth.name to depth,
    Component::builderId.name to builderId,
    Component::type.name to type.name
)

internal fun textComponent(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    depth: Int,
    builderId: String,
    value: String?
) = valueBasedComponent(id, title, identifier, Component.Type.textField, readonly, depth, builderId, value).toTextComponent()

internal fun textFieldComponent(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    depth: Int,
    builderId: String,
    value: String?
) = valueBasedComponent(id, title, identifier, Component.Type.textField, readonly, depth, builderId, value).toTextFieldComponent()

internal fun textFieldAreaComponent(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    depth: Int,
    builderId: String,
    value: String?
) = valueBasedComponent(id, title, identifier, Component.Type.textFieldArea, readonly, depth, builderId, value).toTextFieldAreaComponent()

internal fun signatureComponent(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    depth: Int,
    builderId: String,
    value: String?
) = valueBasedComponent(id, title, identifier, Component.Type.signature, readonly, depth, builderId, value).toSignatureComponent()

internal fun numberFieldComponent(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    depth: Int,
    builderId: String,
    value: Number?
) = valueBasedComponent(id, title, identifier, Component.Type.numberField, readonly, depth, builderId, value?.toDouble()).toNumberFieldComponent()

internal fun dateFieldComponent(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    format: String?,
    depth: Int,
    builderId: String,
    value: Long?
) = mutableMapOf<String, Any?>(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    ValueBasedComponent<*>::value.name to value,
    Component::disabled.name to readonly,
    Component::depth.name to depth,
    Component::builderId.name to builderId,
    DateFieldComponent::format.name to format,
    Component::type.name to Component.Type.dateField.name
).toDateFieldComponent()

internal fun dropdownComponent(
    id: String,
    title: String,
    identifier: String,
    multiple: Boolean,
    options: List<Option2>,
    readonly: Boolean,
    depth: Int,
    builderId: String,
    selected: Option2?
) = mutableMapOf(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    ValueBasedComponent<*>::value.name to selected?.value,
    DropdownComponent::options.name to options.map { it.toMap() }.toMutableList(),
    DropdownComponent::multiple.name to multiple,
    Component::disabled.name to readonly,
    Component::depth.name to depth,
    Component::builderId.name to builderId,
    Component::type.name to Component.Type.dropdown.name
).toDropdownComponent()

internal fun multiSelectComponent(
    id: String,
    title: String,
    identifier: String,
    multiple: Boolean,
    options: List<Option2>,
    depth: Int,
    builderId: String,
    selected: List<String>
) = mutableMapOf<String, Any?>(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    Component::depth.name to depth,
    Component::builderId.name to builderId,
    ValueBasedComponent<*>::value.name to selected,
    MultiSelectComponent::options.name to options.map { it.toMap() }.toMutableList(),
    MultiSelectComponent::multiple.name to multiple,
    Component::type.name to Component.Type.multiSelect.name
).toMultiSelectComponent()

internal fun fileComponent(
    id: String,
    title: String,
    identifier: String,
    depth: Int,
    builderId: String,
    value: List<Attachment>
) = mutableMapOf<String, Any?>(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    Component::depth.name to depth,
    ValueBasedComponent<*>::value.name to value,
    Component::builderId.name to builderId,
    Component::type.name to Component.Type.file.name
).toFileComponent()

internal fun imageComponent(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    depth: Int,
    builderId: String,
    value: List<Attachment>
) = mutableMapOf<String, Any?>(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    Component::depth.name to depth,
    ValueBasedComponent<*>::value.name to value.map { it.toMap() }.toMutableList(),
    Component::disabled.name to readonly,
    Component::builderId.name to builderId,
    Component::type.name to Component.Type.image.name
).toImageComponent()

internal fun tableComponent(
    id: String,
    title: String,
    identifier: String,
    depth: Int,
    builderId: String,
    columns: List<Column>
) = mutableMapOf<String, Any?>(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    Component::depth.name to depth,
    ValueBasedComponent<*>::value.name to mutableListOf<Row>(),
    Component::type.name to Component.Type.table.name,
    Component::builderId.name to builderId,
    COLUMNS to columns.map { it.toMap() }.toMutableList(),
    COLUMN_ORDER to columns.map { it.id }.toMutableList(),
    TableComponent::rowOrder.name to mutableListOf<String>()
).toTableComponent()

internal fun chartComponent(
    id: String,
    title: String,
    identifier: String,
    y: Axis,
    x: Axis,
    depth: Int,
    builderId: String,
    lines: List<Line>
) = mutableMapOf<String, Any?>(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    Component::depth.name to depth,
    AXIS_Y_TITLE to y.label,
    AXIS_Y_MIN to y.min,
    AXIS_Y_MAX to y.max,
    AXIS_X_TITLE to x.label,
    AXIS_X_MIN to x.min,
    AXIS_X_MAX to x.max,
    Component::builderId.name to builderId,
    ValueBasedComponent<*>::value.name to lines.map { it.toMap() }.toMutableList(),
    Component::type.name to Component.Type.chart.name,
).toChartComponent()

internal fun columnComponent(
    id: String,
    title: String,
    identifier: String,
    depth: Int,
    builderId: String,
    components: List<Component>
) = mutableMapOf<String, Any?>(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    Component::depth.name to depth,
    Component::builderId.name to builderId,
    Component::type.name to Component.Type.column.name,
    ValueBasedComponent<*>::value.name to components.map { it.toMap() }.toMutableList()
).toColumnComponent()

internal fun rowComponent(
    id: String,
    title: String,
    identifier: String,
    depth: Int,
    builderId: String,
    components: List<Component>
) = mutableMapOf<String, Any?>(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    Component::depth.name to depth,
    Component::builderId.name to builderId,
    Component::type.name to Component.Type.row.name,
    ValueBasedComponent<*>::value.name to components.map { it.toMap() }.toMutableList()
).toRowComponent()

