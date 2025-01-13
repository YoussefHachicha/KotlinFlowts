package com.youssef.kotlinflowts.builder.kotlinflowts.table

import com.youssef.kotlinflowts.models.kotlinflowts.IdentityGenerator
import com.youssef.kotlinflowts.models.kotlinflowts.components.core.Component
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.Column
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.DropdownColumn
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.ImageColumn
import com.youssef.kotlinflowts.models.kotlinflowts.components.table.TextColumn
import com.youssef.kotlinflowts.models.kotlinflowts.toColumn
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Attachment
import com.youssef.kotlinflowts.models.kotlinflowts.utils.ID
import com.youssef.kotlinflowts.models.kotlinflowts.utils.option
import com.youssef.kotlinflowts.models.kotlinflowts.utils.Option2

internal class TableColumnsBuilderImplTable(
    private val identity: IdentityGenerator
) : TableColumnBuilder {
    val columns = mutableListOf<Column>()

    override fun text(title: String, id: String?, value: String?) {
        val column = mutableMapOf<String, Any?>(
            ID to (id ?: identity.generate()),
            Column::title.name to title,
            Column::type.name to Component.Type.textField.name,
            TextColumn::value.name to value
        ).toColumn()
        columns.add(column)
    }

    override fun dropdown(title: String, options: List<String>, id: String?, value: String?) {
        var default: Option2? = null
        val opts = options.map {
            val option = option(identity.generate(), it)
            if (it == value) {
                default = option
            }
            option
        }

        val column = mutableMapOf(
            ID to (id ?: identity.generate()),
            Column::title.name to title,
            Column::type.name to Component.Type.dropdown.name,
            DropdownColumn::options.name to opts.map { it.toMap() }.toMutableList(),
            DropdownColumn::value.name to default?.id
        ).toColumn()
        columns.add(column)
    }

    override fun image(title: String, id: String?, identifier: String?, value: List<String>) {
        val attachments = value.map { Attachment(id = identity.generate(), url = it) }
        val column = mutableMapOf<String, Any?>(
            ID to (id ?: identity.generate()),
            Column::title.name to title,
            Column::type.name to Component.Type.image.name,
            ImageColumn::value.name to attachments.map { it.toMap() }.toMutableList()
        ).toColumn()
        columns.add(column)
    }
}