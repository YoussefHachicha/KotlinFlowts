package com.youssef.kotlinflowts.builder.joyfill

import com.youssef.kotlinflowts.models.joyfill.ComponentPosition
import com.youssef.kotlinflowts.models.joyfill.File
import com.youssef.kotlinflowts.models.joyfill.MutableFile
import com.youssef.kotlinflowts.models.joyfill.MutableScreen
import com.youssef.kotlinflowts.models.joyfill.Screen
import com.youssef.kotlinflowts.models.joyfill.components.DateComponent
import com.youssef.kotlinflowts.models.joyfill.components.DropdownComponent
import com.youssef.kotlinflowts.models.joyfill.components.core.Component
import com.youssef.kotlinflowts.models.joyfill.components.TableComponent
import com.youssef.kotlinflowts.models.joyfill.components.core.ValueBasedComponent
import com.youssef.kotlinflowts.models.joyfill.components.chart.Axis
import com.youssef.kotlinflowts.models.joyfill.components.chart.Line
import com.youssef.kotlinflowts.models.joyfill.components.table.Column
import com.youssef.kotlinflowts.models.joyfill.components.table.Row
import com.youssef.kotlinflowts.models.joyfill.toChartComponent
import com.youssef.kotlinflowts.models.joyfill.toColumnComponent
import com.youssef.kotlinflowts.models.joyfill.toDateComponent
import com.youssef.kotlinflowts.models.joyfill.toDropdownComponent
import com.youssef.kotlinflowts.models.joyfill.toFile
import com.youssef.kotlinflowts.models.joyfill.toFileComponent
import com.youssef.kotlinflowts.models.joyfill.toImageComponent
import com.youssef.kotlinflowts.models.joyfill.toMultiSelectComponent
import com.youssef.kotlinflowts.models.joyfill.toMutableFile
import com.youssef.kotlinflowts.models.joyfill.toMutableScreen
import com.youssef.kotlinflowts.models.joyfill.toNumberComponent
import com.youssef.kotlinflowts.models.joyfill.toScreen
import com.youssef.kotlinflowts.models.joyfill.toPosition
import com.youssef.kotlinflowts.models.joyfill.toSignatureComponent
import com.youssef.kotlinflowts.models.joyfill.toTableComponent
import com.youssef.kotlinflowts.models.joyfill.toTextAreaComponent
import com.youssef.kotlinflowts.models.joyfill.toTextComponent
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
    format: String?
): ComponentPosition = mutableMapOf<String, Any?>(
    ID to id,
    ComponentPosition::componentId.name to component,
    ComponentPosition::displayType.name to displayType,
    ComponentPosition::format.name to format
).toPosition()

private fun <V> valueBasedComponent(
    id: String,
    title: String,
    identifier: String,
    type: Component.Type,
    readonly: Boolean,
    value: V?
) = mutableMapOf(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    ValueBasedComponent<*>::value.name to value,
    Component::disabled.name to readonly,
    Component::type.name to type.name
)

internal fun textComponent(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    value: String?
) = valueBasedComponent(id, title, identifier, Component.Type.text, readonly, value).toTextComponent()

internal fun textAreaComponent(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    value: String?
) = valueBasedComponent(id, title, identifier, Component.Type.textarea, readonly, value).toTextAreaComponent()

internal fun signatureComponent(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    value: String?
) = valueBasedComponent(id, title, identifier, Component.Type.signature, readonly, value).toSignatureComponent()

internal fun numberComponent(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    value: Number?
) = valueBasedComponent(id, title, identifier, Component.Type.number, readonly, value?.toDouble()).toNumberComponent()

internal fun dateComponent(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    format: String?,
    value: Long?
) = mutableMapOf<String, Any?>(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    ValueBasedComponent<*>::value.name to value,
    Component::disabled.name to readonly,
    DateComponent::format.name to format,
    Component::type.name to Component.Type.date.name
).toDateComponent()

internal fun dropdownComponent(
    id: String,
    title: String,
    identifier: String,
    options: List<Option2>,
    readonly: Boolean,
    selected: Option2?
) = mutableMapOf(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    ValueBasedComponent<*>::value.name to selected?.value,
    DropdownComponent::options.name to options.map { it.toMap() }.toMutableList(),
    Component::disabled.name to readonly,
    Component::type.name to Component.Type.dropdown.name
).toDropdownComponent()

internal fun multiSelectComponent(
    id: String,
    title: String,
    identifier: String,
    options: List<Option2>,
    selected: List<String>
) = mutableMapOf<String, Any?>(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    ValueBasedComponent<*>::value.name to selected,
    DropdownComponent::options.name to options.map { it.toMap() }.toMutableList(),
    Component::type.name to Component.Type.multiSelect.name
).toMultiSelectComponent()

internal fun fileComponent(
    id: String,
    title: String,
    identifier: String,
    value: List<Attachment>
) = mutableMapOf<String, Any?>(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    ValueBasedComponent<*>::value.name to value,
    Component::type.name to Component.Type.file.name
).toFileComponent()

internal fun imageComponent(
    id: String,
    title: String,
    identifier: String,
    readonly: Boolean,
    value: List<Attachment>
) = mutableMapOf<String, Any?>(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    ValueBasedComponent<*>::value.name to value.map { it.toMap() }.toMutableList(),
    Component::disabled.name to readonly,
    Component::type.name to Component.Type.image.name
).toImageComponent()

internal fun tableComponent(
    id: String,
    title: String,
    identifier: String,
    columns: List<Column>
) = mutableMapOf<String, Any?>(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    ValueBasedComponent<*>::value.name to mutableListOf<Row>(),
    Component::type.name to Component.Type.table.name,
    COLUMNS to columns.map { it.toMap() }.toMutableList(),
    COLUMN_ORDER to columns.map { it.id }.toMutableList(),
    TableComponent::rowOrder.name to mutableListOf<String>()
).toTableComponent()

internal fun columnComponent(
    id: String,
    title: String,
    identifier: String,
    components: List<Component>
) = mutableMapOf<String, Any?>(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    Component::type.name to Component.Type.column.name,
    ValueBasedComponent<*>::value.name to components.map { it.toMap() }.toMutableList()
).toColumnComponent()

internal fun chartComponent(
    id: String,
    title: String,
    identifier: String,
    y: Axis,
    x: Axis,
    lines: List<Line>
) = mutableMapOf<String, Any?>(
    ID to id,
    Component::title.name to title,
    Component::identifier.name to identifier,
    AXIS_Y_TITLE to y.label,
    AXIS_Y_MIN to y.min,
    AXIS_Y_MAX to y.max,
    AXIS_X_TITLE to x.label,
    AXIS_X_MIN to x.min,
    AXIS_X_MAX to x.max,
    ValueBasedComponent<*>::value.name to lines.map { it.toMap() }.toMutableList(),
    Component::type.name to Component.Type.chart.name,
).toChartComponent()