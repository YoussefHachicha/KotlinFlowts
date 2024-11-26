package com.youssef.kotlinflowts.models.kotlinflowts

interface Screen : Mappable {
    val id: String
    val name: String
    val positions: List<ComponentPosition>

    fun generateCode(componentsCode: String): String {
        return """
            @Composable
            fun ${name.replace(" ", "")}() {
                 LazyColumn {
                    item {
                       $componentsCode
                    }
                 }    
            }
        """.trimIndent()

    }
}