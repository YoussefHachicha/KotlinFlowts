package com.youssef.kotlinflowts.builder.joyfill.table

interface TableColumnBuilder {
    fun text(
        title: String = "Text Column",
        id: String? = null,
        value: String? = null
    )

    fun dropdown(
        title: String = "Dropdown Column",
        options: List<String> = emptyList(),
        id: String? = null,
        value: String? = null
    )

    fun image(
        title: String = "Image Column",
        id: String? = null,
        identifier: String? = null,
        value: List<String> = emptyList()
    )
}