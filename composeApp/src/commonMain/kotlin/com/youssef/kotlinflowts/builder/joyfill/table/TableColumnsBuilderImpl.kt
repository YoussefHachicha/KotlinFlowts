package com.youssef.kotlinflowts.builder.joyfill.table

import com.sun.tools.javac.main.Option
import com.youssef.kotlinflowts.models.joyfill.IdentityGenerator
import com.youssef.kotlinflowts.models.joyfill.fields.Field
import com.youssef.kotlinflowts.models.joyfill.fields.table.Column
import com.youssef.kotlinflowts.models.joyfill.fields.table.DropdownColumn
import com.youssef.kotlinflowts.models.joyfill.fields.table.ImageColumn
import com.youssef.kotlinflowts.models.joyfill.fields.table.TextColumn
import com.youssef.kotlinflowts.models.joyfill.toColumn
import com.youssef.kotlinflowts.models.joyfill.utils.Attachment
import com.youssef.kotlinflowts.models.joyfill.utils.ID
import com.youssef.kotlinflowts.models.joyfill.utils.Option
import com.youssef.kotlinflowts.models.joyfill.utils.Option2

internal class TableColumnsBuilderImpl(
    private val identity: IdentityGenerator
) : ColumnBuilder {
    val columns = mutableListOf<Column>()

    override fun text(title: String, id: String?, value: String?) {
        val column = mutableMapOf<String, Any?>(
            ID to (id ?: identity.generate()),
            Column::title.name to title,
            Column::type.name to Field.Type.text.name,
            TextColumn::value.name to value
        ).toColumn()
        columns.add(column)
    }

    override fun dropdown(title: String, options: List<String>, id: String?, value: String?) {
        var default: Option2? = null
        val opts = options.map {
            val option = Option(identity.generate(), it)
            if (it == value) {
                default = option
            }
            option
        }

        val column = mutableMapOf(
            ID to (id ?: identity.generate()),
            Column::title.name to title,
            Column::type.name to Field.Type.dropdown.name,
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
            Column::type.name to Field.Type.image.name,
            ImageColumn::value.name to attachments.map { it.toMap() }.toMutableList()
        ).toColumn()
        columns.add(column)
    }
}